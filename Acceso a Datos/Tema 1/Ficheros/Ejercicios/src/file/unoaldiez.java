package file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class unoaldiez {
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\Alumno\\Desktop\\texto.txt"));
            writer.println("Número | Cuadrado");
            writer.println("-----------------");
            for (int i = 1; i <= 10; i++) {
            	 writer.printf(i + " | " + i * i + "\n");            }
            writer.close();
            System.out.println("Números y sus cuadrados escritos.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error.");
            e.printStackTrace();
        }
    }
}