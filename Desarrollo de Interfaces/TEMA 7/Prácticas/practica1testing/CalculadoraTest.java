package practica1testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CalculadoraTest {
    private final Calculadora calc = new Calculadora();
    @Test
    public void testSumar() {
        assertEquals(5, calc.sumar(2, 3));
        assertEquals(-1, calc.sumar(-2, 1));
    }
    @Test
    public void testRestar() {
        assertEquals(1, calc.restar(3, 2));
        assertEquals(-3, calc.restar(-2, 1));
    }
    @Test
    public void testMultiplicar() {
        assertEquals(6, calc.multiplicar(2, 3));
        assertEquals(0, calc.multiplicar(0, 5));
    }
    @Test
    public void testDividir() {
        assertEquals(2.0, calc.dividir(6, 3));
        assertThrows(IllegalArgumentException.class, () -> calc.dividir(5, 0));
    }
    @Test
    public void testFactorial() {
        assertEquals(120, calc.factorial(5));
        assertEquals(1, calc.factorial(0));
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }
    @Test
    public void testEsPrimo() {
        assertTrue(calc.esPrimo(7));
        assertFalse(calc.esPrimo(4));
        assertFalse(calc.esPrimo(1));
    }
}
