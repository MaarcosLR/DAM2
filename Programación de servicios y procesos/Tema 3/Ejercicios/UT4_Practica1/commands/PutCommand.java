package UT4_Practica1.commands;

import java.io.*;
import java.io.PrintWriter;

public class PutCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        if (args.length < 2) {
            salida.println("ERROR: Debes proporcionar un nombre de archivo.");
            return;
        }

        File archivo = new File(args[1]);
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            salida.println("OK: Esperando contenido del archivo...");
            // Aquí deberíamos recibir líneas del cliente y escribirlas en el archivo.
        } catch (IOException e) {
            salida.println("ERROR: No se pudo guardar el archivo.");
        }
    }
}
