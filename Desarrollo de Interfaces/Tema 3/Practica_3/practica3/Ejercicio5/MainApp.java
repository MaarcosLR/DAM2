package practica3.Ejercicio5;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        // Cargar la librería de OpenCV
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);

        // Crear el botón inteligente
        SmartButton button = new SmartButton("Botón Inteligente");

        // Crear la ventana para el botón
        JFrame frame = new JFrame("Prueba de Reconocimiento de Mano y Voz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
