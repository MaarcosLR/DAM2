package file;

import java.io.*;

public class BufferReader {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new FileReader("C:\\Users\\Alumno\\Desktop\\Examen enunciados Acceso a datos\\TiemposOlimpicos.txt"));
		try {
		String linea; 
		linea=br.readLine();
		System.out.println(linea);
		} catch (IOException e) {
			System.out.print("Error al leer el archivo: " + e.getMessage());
		}
	}
}