import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerExample extends JFrame implements KeyListener {
    
    private JTextField textField;
    private JLabel label;

    public KeyListenerExample() {
        // Configurar el JFrame
        setTitle("Ejemplo de KeyListener");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Crear el campo de texto
        JTextField textField = new JTextField(20);
        textField.addKeyListener(this);  // Añadir el KeyListener al JTextField

        // Crear una etiqueta para mostrar las teclas presionadas
        label = new JLabel("Presiona una tecla...");

        // Añadir los componentes al frame
        add(textField);
        add(label);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método que se invoca cuando se presiona una tecla
    @Override
    public void keyPressed(KeyEvent e) {
        label.setText("Tecla presionada: " + KeyEvent.getKeyText(e.getKeyCode()));
   }

    // Método que se invoca cuando se libera una tecla
    @Override
    public void keyReleased(KeyEvent e) {
       label.setText("Tecla liberada: " + KeyEvent.getKeyText(e.getKeyCode()));
   }

    // Método que se invoca cuando se escribe un carácter
    @Override
    public void keyTyped(KeyEvent e) {
    	label.setText("Tecla pulsada: " + e.getKeyChar());
        // Este método se usa cuando se escribe un carácter (letras, números, etc.)
        // No se maneja la tecla modificadora (Shift, Ctrl, etc.)
    }

    public static void main(String[] args) {
        // Crear la ventana del ejemplo
        new KeyListenerExample();
    }
}
