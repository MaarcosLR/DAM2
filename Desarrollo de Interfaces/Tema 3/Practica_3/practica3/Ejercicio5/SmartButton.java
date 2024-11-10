package practica3.Ejercicio5;

import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.Configuration;
import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;

public class SmartButton extends JButton {
    private static final String HAND_CASCADE_PATH = "libraries/haarcascade_hand.xml"; // Ruta del clasificador
    private boolean handDetected = false;
    private boolean voiceActivated = false;
    private LiveSpeechRecognizer recognizer;

    public SmartButton(String text) {
        super(text);
        this.setPreferredSize(new Dimension(200, 100));
        this.setBackground(Color.LIGHT_GRAY); // Color predeterminado
        this.addActionListener(e -> {
            this.setBackground(Color.GREEN); // Cambia a verde al hacer clic
            voiceActivated = false; // Aseguramos que no esté activado por voz al hacer clic
            System.out.println("¡Botón presionado!");
        });
        initVoiceRecognition();
        initGestureDetection();
    }

    // Inicializa el reconocimiento de voz con las configuraciones especificadas
    private void initVoiceRecognition() {
        try {
            Configuration configuration = new Configuration();

            // Establecer las rutas de los modelos en español
            configuration.setAcousticModelPath("file:libraries/resources/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("file:libraries/resources/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            configuration.setLanguageModelPath("file:libraries/resources/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

            // Crear el reconocedor de voz con la configuración
            recognizer = new LiveSpeechRecognizer(configuration);
            recognizer.startRecognition(true);

            new Thread(() -> {
                while (true) {
                    SpeechResult result = recognizer.getResult();
                    if (result != null) {
                        String command = result.getHypothesis().toLowerCase();
                        System.out.println("Comando de voz recibido: " + command);
                        if (command.contains("clic") && !voiceActivated) {
                            this.setBackground(Color.GREEN); // Cambia a verde al activar por voz
                            voiceActivated = true; // Marca como activado por voz
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Inicializa la detección de gestos de mano
    private void initGestureDetection() {
        // Cargar la librería de OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Crear el objeto para la detección de la mano
        CascadeClassifier handCascade = new CascadeClassifier(HAND_CASCADE_PATH);
        if (handCascade.empty()) {
            System.out.println("Error: No se pudo cargar el clasificador de la mano.");
            return;
        }

        // Iniciar la captura de video
        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("Error: No se puede abrir la cámara.");
            return;
        }

        // Crear matrices para la imagen
        Mat frame = new Mat();
        Mat grayFrame = new Mat();
        MatOfRect hands = new MatOfRect(); // Para almacenar las manos detectadas

        // Bucle principal de captura y detección de gestos
        new Thread(() -> {
            while (true) {
                capture.read(frame);
                if (frame.empty()) {
                    System.out.println("Error: No se pudo capturar un fotograma.");
                    break;
                }

                // Convertir la imagen a escala de grises
                Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
                Imgproc.equalizeHist(grayFrame, grayFrame);

                // Detectar las manos
                handCascade.detectMultiScale(grayFrame, hands, 1.1, 3, 0, new Size(30, 30), new Size());

                // Solo si se detecta una mano, cambiar el color del botón
                if (hands.toArray().length > 0) {
                    if (!handDetected) {
                        this.setBackground(Color.GREEN); // Cambio de color del botón
                        handDetected = true;
                        voiceActivated = false; // Aseguramos que no esté activado por voz al detectar mano
                        System.out.println("¡Mano detectada!");
                    }
                } else {
                    if (!voiceActivated && !handDetected) {
                        this.setBackground(Color.LIGHT_GRAY); // Restaurar color del botón si no se detecta mano ni se ha activado
                    }
                    handDetected = false;
                }

                // Mostrar el fotograma
                HighGui.imshow("Camera Feed", frame);
                if (HighGui.waitKey(1) == 27) { // Salir si se presiona 'Esc'
                    break;
                }
            }
            // Liberar recursos
            capture.release();
            HighGui.destroyAllWindows();
        }).start();
    }

    public static void main(String[] args) {
        // Cargar la librería de OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Crear el botón inteligente
        SmartButton button = new SmartButton("Botón Inteligente");

        // Crear la ventana para el botón
        JFrame frame = new JFrame("Prueba de Reconocimiento de Mano y Voz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
