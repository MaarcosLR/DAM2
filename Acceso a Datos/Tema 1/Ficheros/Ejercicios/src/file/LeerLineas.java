package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeerLineas {
	public static void main(String[] args) {
		try {
			File archivo = new File("C:\\Users\\Alumno\\Desktop\\texto.txt");
			Scanner lector = new Scanner(archivo);
			int lineas = 0;
			while (lector.hasNextLine()) {
				String datos = lector.nextLine();
				System.out.println(datos);
				lineas++;
			}
			System.out.println("");
			System.out.println("Número de líneas: " + lineas);
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		}
	}
}
