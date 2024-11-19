//package practica3;
//
//import org.artoolkitx.arx.ARActivity;
//import org.artoolkitx.arx.ARController;
//import org.artoolkitx.arx.rendering.ARRenderer;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//public class practica1.Ejercicio4 extends JFrame {
//
//    private ARController arController;
//    private int markerID;
//
//    public ARInterfaceSwing() {
//        setTitle("Interfaz de Realidad Aumentada");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Crear el panel de RA y añadirlo a la ventana
//        ARPanel arPanel = new ARPanel();
//        add(arPanel, BorderLayout.CENTER);
//
//        // Inicializar ARToolKit y registrar el marcador
//        initAR();
//    }
//
//    private void initAR() {
//        arController = new ARController();
//        arController.initialise();
//
//        // Registrar el marcador
//        markerID = arController.addMarker("single;Data/marker.patt;80");
//        if (markerID < 0) {
//            System.out.println("No se pudo registrar el marcador.");
//        }
//    }
//
//    class ARPanel extends JPanel {
//        private ARRenderer arRenderer;
//
//        public ARPanel() {
//            setPreferredSize(new Dimension(800, 600));
//            arRenderer = new ARRenderer();
//
//            // Iniciar la renderización en un hilo separado
//            new Thread(() -> {
//                while (true) {
//                    if (arController.isRunning()) {
//                        arController.captureNextFrame();
//                        arRenderer.draw();
//                        repaint();
//                    }
//                }
//            }).start();
//        }
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            // Renderiza el video y el objeto 3D en el marcador
//            arRenderer.render(g);
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ARInterfaceSwing frame = new ARInterfaceSwing();
//            frame.setVisible(true);
//        });
//    }
//}
