package Excepciones;
public class ArrayException {
    public static void main(String[] args) {
        int[] Array = {1, 2, 3, 4, 5};
        try {
            int valor = Array[10];
            System.out.println("El valor en el índice 10 es: " + valor);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: El valor del Array '10' no existe");
        }
    }
}
