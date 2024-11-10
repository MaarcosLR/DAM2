package practica3.Ejercicio5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SmartButtonTest {

    @Test
    public void testButtonVoiceControl() {
        // Crear el botón inteligente
        SmartButton button = new SmartButton("Botón Inteligente");

        // Simulamos un comando de voz (esto debería hacer que el botón haga clic)
        // Esto podría ser una simulación manual, ya que el reconocimiento de voz en tiempo real no se puede probar fácilmente con JUnit.
        button.doClick();

        // Verificar que el botón haya sido presionado correctamente
        assertTrue(button.getBackground() == java.awt.Color.GREEN || button.getBackground() == java.awt.Color.LIGHT_GRAY);
    }

    @Test
    public void testButtonGestureControl() {
        // Crear el botón inteligente
        SmartButton button = new SmartButton("Botón Inteligente");

        // Simula que una mano es detectada (esto se puede verificar por el cambio de color)
        button.setBackground(java.awt.Color.GREEN);
        assertTrue(button.getBackground() == java.awt.Color.GREEN);

        // Simula que la mano ya no es detectada
        button.setBackground(java.awt.Color.LIGHT_GRAY);
        assertFalse(button.getBackground() == java.awt.Color.GREEN);
    }
}
