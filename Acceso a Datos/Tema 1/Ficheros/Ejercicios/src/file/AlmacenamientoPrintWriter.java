package file;

import java.io.IOException;
import java.io.PrintWriter;

public class AlmacenamientoPrintWriter {
	final static int resultado = 27+35;
	public static void main(String[] args) {
		try {
		PrintWriter escritor = new PrintWriter("C:\\Users\\Alumno\\Desktop\\texto.txt");
		escritor.println("Texto con PrintWriter");
		escritor.println("Operación");
		escritor.println("27");
		escritor.println("*");
		escritor.println("35");
		escritor.println("=");
		escritor.println(resultado);
		escritor.close();
		} catch (IOException e) {
			System.out.println("Se ha producido un error");
		}
	}
}
