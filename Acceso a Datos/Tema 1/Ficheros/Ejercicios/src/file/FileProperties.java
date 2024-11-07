package file;

import java.io.File;

public class FileProperties {
	public static void main(String[] args) {
		
		File archivo = new File("C:/Users/Marcos/Desktop/ingles_ejercicios.txt");
		String nombre = archivo.getName();
		String ruta = archivo.getPath();
		String rutabsoluta = archivo.getAbsolutePath();
		Boolean leer = archivo.canRead();
		Boolean escribir = archivo.canWrite();
		double tamañoMB = archivo.length() / 1000000.0;
		
		if (archivo.exists()) {
			System.out.println("Nombre del archivo: " + nombre);
			System.out.println("Ruta del archivo: " + ruta);
			System.out.println("Ruta absoluta: " + rutabsoluta);
			System.out.println("¿Se puede leer?: " + leer);
			System.out.println("¿Se puede escribir? " + escribir);
			System.out.println("Tamaño del archivo: " + tamañoMB + "MB");
		} else {
			System.out.println("El archivo no existe.");
		}
	}
}
