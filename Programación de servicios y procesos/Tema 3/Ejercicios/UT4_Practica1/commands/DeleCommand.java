package UT4_Practica1.commands;

import java.io.File;
import java.io.PrintWriter;

public class DeleCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        if (args.length < 2) {
            salida.println("ERROR: Debes proporcionar un nombre de archivo.");
            return;
        }

        File archivo = new File(args[1]);
        if (archivo.exists() && archivo.isFile()) {
            if (archivo.delete()) {
                salida.println("Archivo " + args[1] + " eliminado con éxito.");
            } else {
                salida.println("ERROR: No se pudo eliminar el archivo.");
            }
        } else {
            salida.println("ERROR: El archivo no existe.");
        }
    }
}
