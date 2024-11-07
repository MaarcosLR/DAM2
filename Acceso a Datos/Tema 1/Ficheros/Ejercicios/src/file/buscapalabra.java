package file;

import java.io.*;

public class buscapalabra {
	public static void main(String[] args) throws IOException{
		File archivo = new File ("C://Users//Marcos//Desktop//texto.txt");
		BufferedReader br = new BufferedReader (new FileReader(archivo));
		try {
		String linea; 
		String palabrabuscar = "razón";
		int contador = 0;
		int contadorpalabra = 0;
		while ((linea = br.readLine()) != null && contador != -1) {
			System.out.println(linea);
			String lineaminus = linea.toLowerCase();
			contador++;
			while ((lineaminus.contains(palabrabuscar))) {
				contadorpalabra++;
				lineaminus = lineaminus.replaceFirst(palabrabuscar, "");
			}
		}
		System.out.println("\nLa palabra " + "'" + palabrabuscar + "'" + ", aparece " + contadorpalabra + " veces.");
		System.out.println("\nSe ha usado el BufferReader " + contador + " veces.");
		} catch (IOException e) {
			System.out.print("Error al leer el archivo: " + e.getMessage());
		}
	}
}