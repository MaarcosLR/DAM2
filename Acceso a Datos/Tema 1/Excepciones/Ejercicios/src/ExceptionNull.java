package excepciones;
public class ExceptionNull {
	public static void main(String[] args) {
		try {
			String text = "Marcos";
			int longitud = text.length();
		} catch (NullPointerException e) {
			System.out.println("Es inválido.");
		} finally {
			System.out.println("Fin del programa");
		}
	}
}
