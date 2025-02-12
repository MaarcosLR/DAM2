package practica1testing;

public class Calculadora_errores {
    // Suma dos números (ERROR: retorna resta en lugar de suma)
    public int sumar(int a, int b) {
        return a - b;
    }
    // Resta dos números (ERROR: retorna suma en lugar de resta)
    public int restar(int a, int b) {
        return a + b;
    }
    // Multiplica dos números (ERROR: siempre retorna 0)
    public int multiplicar(int a, int b) {
        return 0;
    }
    // Divide dos números (ERROR: no maneja división por cero correctamente)
    public double dividir(int a, int b) {
        return (double) a / b; // No lanza excepción si b == 0
    }
    // Calcula el factorial (ERROR: inicia con 0 en lugar de 1)
    public int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("No se puede calcular el factorial de un número negativo");
        }
        int resultado = 0; // ERROR aquí, debe ser 1
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
    // Determina si un número es primo (ERROR: considera todos los números impares como primos)
    public boolean esPrimo(int n) {if (n < 2) return false;
        if (n % 2 != 0) return true; // ERROR aquí, ignora otros factores
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
