package Excepciones;
public class LongitudTexto {
    public static void main(String[] args) {
        String texto = null;
        try {
            int longitud = texto.length();
            System.out.println("Longitud del texto: " + longitud);
        } catch (NullPointerException e) {
            System.out.println("Error: Se intentó llamar a un método en un objeto null");
        }
    }
}
