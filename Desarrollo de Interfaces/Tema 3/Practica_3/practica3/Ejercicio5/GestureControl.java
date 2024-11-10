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
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        camera = new VideoCapture(0);
        handCascade = new CascadeClassifier("path/to/haarcascade_hand.xml");
    }

    public void startGestureRecognition() {
        new Thread(() -> {
            while (true) {
                Mat frame = new Mat();
                if (camera.read(frame)) {
                    if (detectHandGesture(frame)) {
                        button.triggerClick();
                        System.out.println("Gesto detectado: Activar");
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

