package practica3.Ejercicio5;

import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

public class GestureControl {
    private SmartButton button;
    private VideoCapture camera;
    private CascadeClassifier handCascade;

    public GestureControl(SmartButton button) {
        this.button = button;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  // Cargar la biblioteca nativa de OpenCV
        camera = new VideoCapture(0);  // Inicia la cámara
        handCascade = new CascadeClassifier("libraries/haarcascade_hand.xml");  // Ruta al archivo del clasificador Haar
    }

    public void startGestureRecognition() {
        new Thread(() -> {
            while (true) {
                Mat frame = new Mat();
                if (camera.read(frame)) {
                    if (detectHandGesture(frame)) {
                        button.activateByGesture();
                        System.out.println("Gesto de mano detectado: Activar");
                    }
                }
            }
        }).start();
    }

    private boolean detectHandGesture(Mat frame) {
        MatOfRect hands = new MatOfRect();
        handCascade.detectMultiScale(frame, hands);

        return hands.toArray().length > 0;
    }
}
