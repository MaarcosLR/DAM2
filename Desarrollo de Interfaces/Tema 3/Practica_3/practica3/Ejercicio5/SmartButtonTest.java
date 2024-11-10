package practica3.Ejercicio5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SmartButtonTest {

    @Test
    public void testVoiceActivation() {
        SmartButton button = new SmartButton("Botón Inteligente");
        VoiceControl voiceControl = new VoiceControl(button);
        button.setVoiceEnabled(true);

        // Simulación del comando de voz
        voiceControl.startVoiceRecognition();
        assertTrue(button.isEnabled(), "El botón debería estar activado por voz");
    }

    @Test
    public void testGestureActivation() {
        SmartButton button = new SmartButton("Botón Inteligente");
        GestureControl gestureControl = new GestureControl(button);
        button.setGestureEnabled(true);

        // Simulación del gesto de la mano
        gestureControl.startGestureRecognition();
        assertTrue(button.isEnabled(), "El botón debería estar activado por gesto");
    }
}
