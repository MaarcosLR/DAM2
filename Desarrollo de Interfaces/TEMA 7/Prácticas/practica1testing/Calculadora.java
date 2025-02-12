package practica1testing;

public class Calculadora {
    // Suma dos números
    public int sumar(int a, int b) {
        return a + b;
    }
    // Resta dos números
    public int restar(int a, int b) {
        return a - b;
    }
    // Multiplica dos números
    public int multiplicar(int a, int b) {
        return a * b;
    }
    // Divide dos números con control de división por cero
    public double dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("No se puede dividir por cero");
        }
        return (double) a / b;
    }
    // Calcula el factorial de un número con control de entrada negativa
    public int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("No se puede calcular el factorial de un número negativo");
        }
        int resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
    // Determina si un número es primo
    public boolean esPrimo(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}