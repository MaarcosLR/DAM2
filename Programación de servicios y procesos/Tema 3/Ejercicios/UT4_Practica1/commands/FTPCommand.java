package UT4_Practica1.commands;

import java.io.PrintWriter;

public interface FTPCommand {
    void execute(String[] args, PrintWriter salida);
}
