//package practica3;
//import org.opencv.core.*;
//import org.opencv.core.Point;
//import org.opencv.videoio.VideoCapture;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.objdetect.CascadeClassifier;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//
//public class practica1.Ejercicio3 extends JFrame {
//    private JLabel imageLabel;
//    private VideoCapture camera;
//    private CascadeClassifier handCascade;
//
//    public practica1.Ejercicio3() {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Cargar la biblioteca de OpenCV
//        camera = new VideoCapture(0); // Iniciar la cámara
//
//        // Cargar el clasificador para la detección de manos
//        handCascade = new CascadeClassifier("libraries/haarcascade_hand.xml");
//
//        // Configuración de la ventana
//        imageLabel = new JLabel();
//        add(imageLabel);
//        setTitle("Detección de Gestos");
//        setSize(640, 480);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//
//        // Hilo para capturar y procesar la imagen de la cámara
//        new Thread(() -> {
//            while (true) {
//                Mat frame = new Mat();
//                if (camera.read(frame)) {
//                    detectHandGesture(frame);
//                    BufferedImage img = matToBufferedImage(frame);
//                    imageLabel.setIcon(new ImageIcon(img));
//                }
//            }
//        }).start();
//    }
//
//    private void detectHandGesture(Mat frame) {
//        MatOfRect hands = new MatOfRect();
//        handCascade.detectMultiScale(frame, hands);
//
//        // Dibujar un rectángulo en la detección de manos
//        for (Rect rect : hands.toArray()) {
//            Imgproc.rectangle(frame, new Point(rect.x, rect.y),
//                    new Point(rect.x + rect.width, rect.y + rect.height),
//                    new Scalar(0, 255, 0), 2);
//        }
//    }
//
//    private BufferedImage matToBufferedImage(Mat mat) {
//        int type = BufferedImage.TYPE_BYTE_GRAY;
//        if (mat.channels() > 1) {
//            type = BufferedImage.TYPE_3BYTE_BGR;
//        }
//        int bufferSize = mat.channels() * mat.cols() * mat.rows();
//        byte[] buffer = new byte[bufferSize];
//        mat.get(0, 0, buffer);
//        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
//        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
//        return image;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(practica1.Ejercicio3::new);
//    }
//}
