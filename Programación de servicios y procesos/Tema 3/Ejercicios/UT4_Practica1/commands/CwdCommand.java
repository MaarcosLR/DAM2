package UT4_Practica1.commands;

import java.io.File;
import java.io.PrintWriter;

public class CwdCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        if (args.length < 2) {
            salida.println("ERROR: Debes proporcionar un directorio.");
            return;
        }

        File nuevoDirectorio = new File(args[1]);
        if (nuevoDirectorio.isDirectory() && nuevoDirectorio.exists()) {
            // Cambiar el directorio de trabajo para el servidor
            System.setProperty("user.dir", nuevoDirectorio.getAbsolutePath());  // Cambia la propiedad de directorio
            salida.println("Directorio cambiado a: " + nuevoDirectorio.getAbsolutePath());
        } else {
            salida.println("ERROR: No se pudo cambiar al directorio especificado.");
        }
    }
}
