package UT4_Practica1.commands;

import java.io.PrintWriter;

public class CommandExecutor {

    // Cambié la firma para aceptar el arreglo de argumentos
    public void executeCommand(FTPCommand command, String[] args, PrintWriter salida) {
        try {
            // Ejecutar el comando pasando los argumentos y la salida para la respuesta
            command.execute(args, salida);
        } catch (Exception e) {
            salida.println("ERROR: No se pudo ejecutar el comando.");
        }
    }
}
