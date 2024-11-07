package file;
import java.io.*;
public class LeerArchivo {
public static void main(String[] args) {
	try {
		FileReader fr = new FileReader("C:\\Users\\Alumno\\Desktop\\texto.txt");
		int i;
		int contador = 0;
		while ((i = fr.read()) != -1 && contador != -1) {
			System.out.print((char) + i);
			if(i == 32) {
				contador++;
			}
		}
		System.out.print("\nSe ha usado el FileReader " + contador + " veces.");
		fr.close();	
	} catch (IOException e) {
		System.out.print("Error al leer el archivo: " + e.getMessage());
	}
}
}
