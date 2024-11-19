package Excepciones;
import java.util.Scanner;

public class ErrorNumeroIntroducido {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        try {
            int number = Integer.parseInt(text);
            System.out.println("El número es " + number);
        }
        catch (NumberFormatException e) {
            System.out.println("Error: No se puede pasar de cadena a numeros enteros");
        }
        System.out.println("Con el error sigue funcionando :)");
    }
}