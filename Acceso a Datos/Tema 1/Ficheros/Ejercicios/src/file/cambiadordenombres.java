package file;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class cambiadordenombres {
    public static void main(String[] args) {
    	
        String rutaDirectorio = "C:/Users/Marcos/Desktop/DAM2/Acceso a Datos/imagenes pruebas";
        File directorio = new File(rutaDirectorio);
        
        if (directorio.exists() && directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null && archivos.length > 0) {
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
                String fechaHoraFormateada = fechaHoraActual.format(formatter);

                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        String nuevoNombre = fechaHoraFormateada + "_" + archivo.getName();
                        File nuevoArchivo = new File(directorio, nuevoNombre);
                        boolean renombrado = archivo.renameTo(nuevoArchivo);
                        if (renombrado) {
                        	System.out.println("Archivos renombrados correctamente.");
                        } else {
                        	System.out.println("No se han podido renombrar los archivos.");
                        }
                    }
                }
            } else {
                System.out.println("El directorio está vacío.");
            }
        } else {
            System.out.println("La ruta proporcionada no es un directorio válido o no existe.");
        }
    }
}
