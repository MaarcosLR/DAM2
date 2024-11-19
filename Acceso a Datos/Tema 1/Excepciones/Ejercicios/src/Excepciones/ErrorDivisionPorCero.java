package Excepciones;
public class ErrorDivisionPorCero {
    public static void main(String[] args) {
        int a = 0;
        int b = 10;
        int operacion = a / b;
        if (a == 0){
            throw new ArithmeticException("No quiero que dividas pq el numerador = 0");
        }
    }
}