package file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Encriptador {
    public static void main(String[] args) {
        int desplazamiento = 3; // Desplazamiento para el cifrado César
        
        try {
            // Leer el contenido del archivo
            FileReader fr = new FileReader("C:\\Users\\Marcos\\Desktop\\texto.txt");
            StringBuilder contenido = new StringBuilder(); // Almacenar el contenido leído
            
            int archivo;
            while ((archivo = fr.read()) != -1) { 
                contenido.append((char) archivo); // Guardar cada carácter en el StringBuilder
            }
            fr.close(); // Cerrar el FileReader

            // Cifrar el contenido
            String textoCifrado = cifrarTexto(contenido.toString(), desplazamiento);

            // Escribir el contenido cifrado de vuelta al archivo
            FileWriter fw = new FileWriter("C:\\Users\\Marcos\\Desktop\\texto.txt");
            fw.write(textoCifrado);
            fw.close(); // Cerrar el FileWriter
            
            System.out.println("Archivo modificado exitosamente.");
            System.out.println("\nContenido cifrado:\n" + textoCifrado);

        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    // Método para cifrar el texto utilizando el cifrado César
    public static String cifrarTexto(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            char base;

            // Determinar la base según si es mayúscula o minúscula
            if (Character.isUpperCase(caracter)) {
                base = 'A';
            } else if (Character.isLowerCase(caracter)) {
                base = 'a';
            } else {
                // Si no es letra, simplemente agregar el carácter sin modificar
                resultado.append(caracter);
                continue; // Continuar con el siguiente carácter
            }

            // Aplicar el desplazamiento y manejar el ciclo del alfabeto
            caracter = (char) ((caracter - base + desplazamiento) % 26 + base);
            resultado.append(caracter); // Agregar el carácter cifrado al resultado
        }

        return resultado.toString(); // Retornar el texto cifrado
    }
}
