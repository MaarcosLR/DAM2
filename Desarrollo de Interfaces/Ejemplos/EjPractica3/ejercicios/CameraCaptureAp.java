//package ejercicios;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//
//
//public class CameraCaptureAp {
////Captura la imagen de la cámara y la convierte a escala de grises
//    static {
//        // Cargar la librería nativa de OpenCV
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//    }
//
//    private JFrame frame;
//    private JLabel imageLabel;
//    private VideoCapture capture;
//    private Mat matFrame;
//
//    public CameraCaptureAp() {
//        // Inicializar la ventana de Swing
//        frame = new JFrame("Captura de Cámara con OpenCV");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(640, 480);
//
//        // Crear el JLabel para mostrar la imagen
//        imageLabel = new JLabel();
//        frame.add(imageLabel, BorderLayout.CENTER);
//        frame.setVisible(true);
//
//        // Inicializar el objeto VideoCapture
//        capture = new VideoCapture(0); // Abrir la cámara 0 (predeterminada)
//        matFrame = new Mat(); // Almacenar el frame capturado
//
//        // Comprobar si la cámara está abierta
//        if (!capture.isOpened()) {
//            System.out.println("Error: No se pudo abrir la cámara.");
//            return;
//        }
//
//        // Hilo para capturar video en tiempo real
//        Thread videoThread = new Thread(() -> {
//            while (capture.isOpened()) {
//                // Leer el siguiente frame
//                if (capture.read(matFrame)) {
//                    // Convertir el frame a escala de grises
//                    Imgproc.cvtColor(matFrame, matFrame, Imgproc.COLOR_BGR2GRAY);
//
//                    // Mostrar el frame en la interfaz
//                    ImageIcon icon = new ImageIcon(matToBufferedImage(matFrame));
//                    imageLabel.setIcon(icon);
//                    frame.repaint();
//
//                    // Esperar un poco antes del siguiente frame
//                    try {
//                        Thread.sleep(30);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        videoThread.start();
//    }
//
//    // Convertir un Mat de OpenCV a BufferedImage de Swing
//    private BufferedImage matToBufferedImage(Mat mat) {
//        int width = mat.width();
//        int height = mat.height();
//        int channels = mat.channels();
//        byte[] sourcePixels = new byte[width * height * channels];
//        mat.get(0, 0, sourcePixels);
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
//        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
//        return image;
//    }
//
//    public static void main(String[] args) {
//        // Iniciar la aplicación
//    	  //Echa un vistazo a https://docs.oracle.com/javase/8/docs/api/javax/swing/SwingUtilities.html
//        SwingUtilities.invokeLater(CameraCaptureAp::new);
//    }
//}

