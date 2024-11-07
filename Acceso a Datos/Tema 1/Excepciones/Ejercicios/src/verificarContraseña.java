package excepciones;
import java.util.Scanner;

class ContraseñaInvalidaException extends Exception{
	public ContraseñaInvalidaException(String mensaje) {
		super(mensaje);
	}
}


public class verificarContraseña {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca una nueva contraseña: ");
		String contraseña = sc.nextLine();
		if (contraseña.length()>8) {
			throw new ContraseñaInvalidaException("La contraseña no puede contener más de 8 carácteres.");
		} else {
			System.out.println("Contraseña establecida, su nueva cotraseña es: " + contraseña);
		}
	}
}
