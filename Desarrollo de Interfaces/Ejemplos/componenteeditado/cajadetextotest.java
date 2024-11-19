package componenteeditado;

import javax.swing.*;

public class cajadetextotest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba de Área de Texto Redonda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        // Usar la clase personalizada cajadetextoredonda en lugar de JTextArea o JButton
        cajadetextoredonda areatxt = new cajadetextoredonda();
        
        // Añadir el área de texto personalizada a la ventana
        frame.add(areatxt);
        frame.setVisible(true);
    }
}
