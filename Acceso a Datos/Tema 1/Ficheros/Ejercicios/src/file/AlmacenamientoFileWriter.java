package file;

import java.io.FileWriter;
import java.io.IOException;

public class AlmacenamientoFileWriter {
	final static int resultado = 27+35;
	public static void main(String[] args) {
		try {
		FileWriter escritor = new FileWriter("C:\\Users\\Alumno\\Desktop\\texto.txt");
		escritor.write("Texto con FileWriter");
		escritor.write("Operacion");
		escritor.write(27);
		escritor.write("*");
		escritor.write(35);
		escritor.write("=");
		escritor.write(resultado);
		escritor.close();
		} catch (IOException e) {
			System.out.println("Se ha producido un error");
		}
	}
}
