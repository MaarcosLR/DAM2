import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseMotionExample extends JFrame {

    public static void main(String[] args) {
        // Crear el marco (JFrame)
        JFrame frame = new JFrame("MouseMotionListener Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Crear un JPanel donde se detectará el movimiento del ratón
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawString("Mueve el ratón, arrástralo o haz clic...", 10, 20);
            }
        };
        
        panel.setBackground(Color.LIGHT_GRAY);
        
        // Etiqueta para mostrar las coordenadas del ratón
        JLabel label = new JLabel("Coordenadas del ratón: ");
        
        // Añadir un MouseListener al panel
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setText("Ratón clicado en la posición: " + e.getX() + ", " + e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                panel.setBackground(Color.blue);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                panel.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // No se necesita implementar
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // No se necesita implementar
            }
        });
        
        // Añadir un MouseMotionListener al panel
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Se llama cuando se arrastra el ratón
                label.setText("Ratón arrastrado a: " + e.getX() + ", " + e.getY());
                panel.setBackground(Color.red);
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                // Se llama cuando el ratón se mueve
                label.setText("Ratón movido a: " + e.getX() + ", " + e.getY());
            }
        });
        
        // Añadir los componentes al marco
        frame.add(panel, BorderLayout.CENTER);
        frame.add(label, BorderLayout.SOUTH); // La etiqueta en la parte inferior
        
        // Mostrar el marco
        frame.setVisible(true);
    }
}
