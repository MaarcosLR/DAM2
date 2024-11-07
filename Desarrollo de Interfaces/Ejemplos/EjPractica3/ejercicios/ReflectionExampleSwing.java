package ejercicios;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;

public class ReflectionExampleSwing {

    public static void main(String[] args) {
        // Crear el JFrame
        JFrame frame = new JFrame("Ejemplo de Reflexión con Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        // Crear un botón
        JButton button = new JButton("Botón Original");

        // Añadir el botón al frame
        frame.add(button);

        // Hacer visible el JFrame
        frame.setVisible(true);

        // Usar Timer para esperar 2 segundos antes de cambiar el botón para que apreciemos cómo cambia
        Timer timer = new Timer(2000, e -> {
            // Modificar el botón usando reflexión
            try {
                // Obtener la clase del botón
                Class<?> buttonClass = button.getClass();
                
                // Obtener el método setText de JButton
                Method setTextMethod = buttonClass.getMethod("setText", String.class);

                // Invocar el método setText para cambiar el texto del botón
                setTextMethod.invoke(button, "Texto Modificado con Reflexión");

                // Obtener el método setBackground de JButton
                Method setBackgroundMethod = buttonClass.getMethod("setBackground", Color.class);

                // Cambiar el color de fondo del botón
                setBackgroundMethod.invoke(button, Color.GREEN);


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Configurar el Timer para que se ejecute solo una vez
        timer.setRepeats(false);
        timer.start(); // Iniciar el Timer
    }
}


