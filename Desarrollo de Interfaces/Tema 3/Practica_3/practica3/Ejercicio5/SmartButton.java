package practica3.Ejercicio5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartButton extends JButton {
    private boolean voiceEnabled;
    private boolean gestureEnabled;

    public SmartButton(String text) {
        super(text);
        this.voiceEnabled = false;
        this.gestureEnabled = false;

        // Acciones para cuando se hace clic en el botón
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón presionado");
            }
        });
    }

    // Habilitar o deshabilitar el control por voz
    public void setVoiceEnabled(boolean voiceEnabled) {
        this.voiceEnabled = voiceEnabled;
        if (voiceEnabled) {
            initializeVoiceRecognition();
        }
    }

    // Habilitar o deshabilitar el control por gestos
    public void setGestureEnabled(boolean gestureEnabled) {
        this.gestureEnabled = gestureEnabled;
        if (gestureEnabled) {
            initializeGestureRecognition();
        }
    }

    private void initializeVoiceRecognition() {
        // Configura reconocimiento de voz (implementación detallada abajo)
        System.out.println("Reconocimiento de voz activado");
    }

    private void initializeGestureRecognition() {
        // Configura reconocimiento de gestos (implementación detallada abajo)
        System.out.println("Reconocimiento de gestos activado");
    }

    // Método para simular un clic programático
    public void triggerClick() {
        doClick();
    }
}

