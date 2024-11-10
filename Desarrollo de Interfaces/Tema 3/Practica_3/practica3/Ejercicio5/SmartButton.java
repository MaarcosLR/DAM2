package practica3.Ejercicio5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartButton extends JButton {

    public SmartButton(String text) {
        super(text);

        // Acción básica al hacer clic
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón presionado manualmente.");
            }
        });
    }

    // Activa el botón por voz
    public void activateByVoice() {
        System.out.println("Botón activado por voz.");
        doClick();  // Simula un clic en el botón
    }

    // Activa el botón por gesto
    public void activateByGesture() {
        System.out.println("Botón activado por gesto.");
        doClick();  // Simula un clic en el botón
    }
}
