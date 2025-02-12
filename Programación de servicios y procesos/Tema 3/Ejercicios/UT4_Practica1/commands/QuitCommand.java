package UT4_Practica1.commands;

import java.io.PrintWriter;

public class QuitCommand implements FTPCommand {
    @Override
    public void execute(String[] args, PrintWriter salida) {
        salida.println("Desconectando... ¡Adiós!");
    }
}
