package file;

import java.io.*;

public class leertodobuffer {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new FileReader("C://Users//Marcos//Desktop//texto.txt"));
		try {
		String linea; 
		int contador = 0;
		while ((linea = br.readLine()) != null && contador != -1) {
			System.out.println(linea);
			contador++;
		}
		System.out.println("\nSe ha usado el BufferReader " + contador + " veces.");
		} catch (IOException e) {
			System.out.print("Error al leer el archivo: " + e.getMessage());
		}
	}
}