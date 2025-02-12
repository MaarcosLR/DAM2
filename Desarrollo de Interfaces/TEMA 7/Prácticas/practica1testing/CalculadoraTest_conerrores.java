package practica1testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CalculadoraTest_conerrores {
    private final Calculadora calc = new Calculadora();

    @Test
    public void testSumar() {
        assertEquals(6, calc.sumar(2, 3)); // ERROR: El resultado correcto es 5
    }

    @Test
    public void testRestar() {
        assertEquals(2, calc.restar(3, 2)); // ERROR: El resultado correcto es 1
    }

    @Test
    public void testMultiplicar() {
        assertEquals(12, calc.multiplicar(2, 3)); // ERROR: El resultado correcto es 6
    }

    @Test
    public void testDividir() {
        assertEquals(3.0, calc.dividir(6, 3)); // ERROR: El resultado correcto es 2.0
    }

    @Test
    public void testFactorial() {
        assertEquals(24, calc.factorial(5)); // ERROR: El resultado correcto es 120
    }

    @Test
    public void testEsPrimo() {
        assertTrue(calc.esPrimo(9)); // ERROR: 9 no es primo
    }

    @Test
    public void testDividirPorCero() {
        assertDoesNotThrow(() -> calc.dividir(5, 0)); // ERROR: Debe lanzar una excepción
    }
}
