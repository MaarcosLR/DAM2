package componenteeditado;

import javax.swing.*;

public class componentetest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba de Botón Redondo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        // Usar la clase personalizada Boton en lugar de JButton
        Boton botonPersonalizado = new Boton("Presióname");
        
        // Añadir el botón personalizado a la ventana
        frame.add(botonPersonalizado);
        frame.setVisible(true);
    }
}
