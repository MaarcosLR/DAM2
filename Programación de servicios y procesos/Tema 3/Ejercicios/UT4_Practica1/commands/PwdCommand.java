package UT4_Practica1.commands;

import java.io.File;
import java.io.PrintWriter;

public class PwdCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        File directorioActual = new File(".");
        salida.println("Directorio actual: " + directorioActual.getAbsolutePath());
    }
}
