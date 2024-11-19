package practica1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio5 {
    public static void main(String[] args) {

        //Crear el Frame
        JFrame frame = new JFrame();

        //Crear el botón
        JButton boton = new JButton("Haz click");

        //Acción para cambiar el texto cuando el boton sea pulsado
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boton.setText("Has hecho click");
            }
        });

        //Añadir botón al frame
        frame.add (boton);

        //Ajustar el tamaño de la ventana
        frame.setSize(300,300);

        //Comportamiento de la ventana al cerrar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Hacer visible la ventana
        frame.setVisible(true);
    }
}
