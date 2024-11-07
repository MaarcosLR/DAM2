package file;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class bufferedwriter {
    public static void main(String[] args) {
        try {
            // Crear un FileWriter que luego se pasa a BufferedWriter
            FileWriter fw = new FileWriter("C://Users//Marcos//Desktop//texto.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("Texto de ejemplo.");
            bw.close();
            System.out.println("Archivo modificado exitosamente.");

            // Leer el archivo con FileReader
            FileReader fr = new FileReader("C://Users//Marcos//Desktop//texto.txt");
            int archivo;
            System.out.println("\nContenido:\n");
            while ((archivo = fr.read()) != -1) {
                System.out.print((char) archivo);
            }
            fr.close();
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();  // Esto te ayudará a depurar el error si ocurre
        }
    }
}
