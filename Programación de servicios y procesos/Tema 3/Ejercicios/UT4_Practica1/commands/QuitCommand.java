package UT4_Practica1.commands;

import java.io.*;

public class QuitCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        PrintWriter writer = new PrintWriter(salida, true);

        writer.println("Desconectando... ¡Adiós!");
    }
}
