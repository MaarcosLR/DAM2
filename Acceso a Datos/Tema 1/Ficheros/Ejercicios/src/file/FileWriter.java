package file;

import java.io.FileReader;
import java.io.IOException;

public class FileWriter {
    public static void main(String[] args) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("C:\\Users\\Alumno\\Desktop\\texto.txt");
            fw.write("Texto de ejemplo.");
            fw.close();
            System.out.println("Archivo modificado exitosamente.");
            FileReader fr = new FileReader("C:\\Users\\Alumno\\Desktop\\texto.txt");
            int archivo;
            System.out.println("\nContenido:\n");
            while ((archivo = fr.read()) != -1) { 
                System.out.print((char) archivo);
            }
            fr.close();
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
        }
    }
}
