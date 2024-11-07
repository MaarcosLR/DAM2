package excepciones;
public class DivisionException {
    public static void main(String[] args) {
        try {
            int num1 = 10;
            int num2 = 0;
            int resultado = num1 / num2;
            System.out.println("El resultado es: " + resultado);
        }
        catch (ArithmeticException e){
            System.out.println("Error: División por cero no permitida.");
        }
        System.out.println("Hola mundo");
    }
}