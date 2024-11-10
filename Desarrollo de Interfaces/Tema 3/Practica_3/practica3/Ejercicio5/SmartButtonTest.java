package practica3.Ejercicio5;

import javax.swing.*;
import java.awt.*;

public class SmartButtonTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba de SmartButton con Voz y Gestos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Crear el SmartButton
        SmartButton smartButton = new SmartButton("Botón Inteligente");

        // Inicializar control de voz y gestos
        VoiceControl voiceControl = new VoiceControl(smartButton);
        GestureControl gestureControl = new GestureControl(smartButton);

        // Iniciar detección de voz y gestos en hilos separados
        new Thread(voiceControl::startVoiceRecognition).start();
        new Thread(gestureControl::startGestureRecognition).start();

        // Añadir el botón a la ventana
        JPanel panel = new JPanel();
        panel.add(smartButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
