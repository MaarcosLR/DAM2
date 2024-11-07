package file;
import java.io.IOException;
import java.io.PrintWriter;

public class EscribirArchivoPrintWriter {
	public static void main(String[] args) {
		try {
			PrintWriter escritor = new PrintWriter("C:\\Users\\Alumno\\Desktop\\prueba.txt");
			escritor.write(20);
			escritor.close();
			System.out.println("Archivo modificado exitosamente.");
		} catch (IOException e) {
			System.out.println("Ocurrió un error.");
		}
	}
}
