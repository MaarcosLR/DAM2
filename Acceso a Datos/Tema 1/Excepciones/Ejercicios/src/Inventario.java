package excepciones;
import java.util.Scanner;

class ProductoDuplicadoException extends Exception {
	public ProductoDuplicadoException(String mensaje){
	super(mensaje);}
}

public class Inventario {
	public static void main(String[] args) throws Exception{
		String manivela = "manivela";
		String cuchara = "cuchara";
		String tenedor = "tenedor";
		Scanner sc = new Scanner(System.in);
		System.out.println("Añada un nuevo producto: ");
		String producto = sc.nextLine();
		
		if(producto == tenedor || producto == manivela || producto == cuchara) {
			throw new ProductoDuplicadoException("El producto ya existe.");
		} else {
			System.out.println("Producto añadido correctamente: ");
		}
	}
}
