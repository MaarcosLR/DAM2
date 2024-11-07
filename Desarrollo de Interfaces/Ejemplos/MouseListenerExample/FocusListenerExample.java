import java.awt.event.*;
import javax.swing.*;

public class FocusListenerExample {

    public static void main(String[] args) {
        // Crear el marco (JFrame)
        JFrame frame = new JFrame("FocusListener Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        
        // Crear un campo de texto (JTextField)
        JTextField textField = new JTextField("Escribe aquí...");
        
        // Agregar el FocusListener al campo de texto
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Cuando el campo de texto gana el foco
                System.out.println("Campo de texto ganó el foco");
                if (textField.getText().equals("Escribe aquí...")) {
                    textField.setText(""); // Limpiar el campo si tiene el texto predeterminado
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Cuando el campo de texto pierde el foco
                System.out.println("Campo de texto perdió el foco");
                if (textField.getText().isEmpty()) {
                    textField.setText("Escribe aquí..."); // Restablecer el texto predeterminado si está vacío
                }
            }
        });
        
        // Añadir el campo de texto al marco
        frame.add(textField);
        
        // Mostrar el marco
        frame.setVisible(true);
    }
}
