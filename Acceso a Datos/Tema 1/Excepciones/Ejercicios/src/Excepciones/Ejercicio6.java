package Excepciones;
import java.util.Scanner;

class PipiolinException extends Exception{
    public PipiolinException(String mensaje) {
        super(mensaje);
    }
}

public class Ejercicio6 {
    public static void main(String[] args) throws Exception {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce tu edad: ");
            int edad = sc.nextInt();
            if (edad < 18) {
                throw new PipiolinException("Eres un Pipiolin.");
            } else {
                System.out.println("Adelante, puede pasar.");
            }
    }
}