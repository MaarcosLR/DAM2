package mouselistenerej;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerExample extends JFrame implements MouseListener{
    
    private JPanel panel;
    private JLabel label;
    
    public MouseListenerExample() {
        // Configurar el JFrame
        setTitle("Ejemplo de MouseListener");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Crear un JPanel
        panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.addMouseListener(this);  // Añadir MouseListener al JPanel

        // Añadir el panel al frame
        add(panel, BorderLayout.CENTER);

        // Hacer visible el JFrame
        setVisible(true);
        
        //Crear el label
        label = new JLabel();
        
        //Añadir el label al panel que esta añadido al frame
        panel.add(label);
    }
    

    // Método invocado cuando se hace clic con el ratón
    @Override
    public void mouseClicked(MouseEvent e) {
        // Cambiar el color del panel al hacer clic
        panel.setBackground(Color.BLUE);
    	label.setText("Ratón clicado en la posición: " + e.getX() + ", " + e.getY());
    }

    // Método invocado cuando se presiona el botón del ratón
    @Override
    public void mousePressed(MouseEvent e) {
        panel.setBackground(Color.GREEN);
        label.setText("Ratón presionado");
    }

    // Método invocado cuando se libera el botón del ratón
    @Override
    public void mouseReleased(MouseEvent e) {
        panel.setBackground(Color.YELLOW);
        label.setText("Ratón liberado");
    }

    // Método invocado cuando el ratón entra al área del componente
    @Override
    public void mouseEntered(MouseEvent e) {
        panel.setBackground(Color.ORANGE);
        label.setText("Ratón ha entrado en el panel");
    }

    // Método invocado cuando el ratón sale del área del componente
    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBackground(Color.LIGHT_GRAY);
        label.setText("Ratón ha salido del panel");
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        new MouseListenerExample();
    }
}
