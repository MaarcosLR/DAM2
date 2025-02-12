package UT4_Practica1.commands;

import java.io.*;

public class GetCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        if (args.length < 2) {
            salida.println("ERROR: Debes proporcionar un nombre de archivo.");
            return;
        }

        // Ruta del archivo a enviar
        File archivo = new File(args[1]);
        if (!archivo.exists() || archivo.isDirectory()) {
            salida.println("ERROR: Archivo no encontrado.");
            return;
        }

        // Notificar al cliente que el archivo va a ser enviado
        salida.println("OK: Enviando archivo " + archivo.getName());

        // Enviar el archivo al cliente en bloques
        try (FileInputStream fis = new FileInputStream(archivo);
             BufferedOutputStream out = new BufferedOutputStream(salida)) {
            byte[] buffer = new byte[4096];  // Tamaño del buffer de 4KB
            int bytesRead;

            // Leer el archivo en bloques y enviarlo al cliente
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();  // Asegurarse de que todos los datos se envíen
            salida.println("EOF"); // Señal de fin de archivo

        } catch (IOException e) {
            salida.println("ERROR: No se pudo leer el archivo.");
        }
    }
}
