package UT4_Practica1.commands;

import java.io.BufferedReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private final Map<String, Class<? extends FTPCommand>> commandMap = new HashMap<>();
    private String username;

    public CommandParser() {
        commandMap.put("USER", UserCommand.class);
        commandMap.put("PASS", PassCommand.class);
        commandMap.put("LIST", ListCommand.class);
        commandMap.put("PWD", PwdCommand.class);
        commandMap.put("CWD", CwdCommand.class);
        commandMap.put("GET", GetCommand.class);
        commandMap.put("PUT", PutCommand.class);
        commandMap.put("DELE", DeleCommand.class);
        commandMap.put("QUIT", QuitCommand.class);
    }

    public FTPCommand getCommand(String commandName, String argument, Socket clientSocket, BufferedReader input) {
        Class<? extends FTPCommand> commandClass = commandMap.get(commandName.toUpperCase());
        if (commandClass == null) {
            return null;
        }

        try {
            if (commandClass == UserCommand.class) {
                return new UserCommand(argument); // Solo el argumento para USER
            } else if (commandClass == PassCommand.class) {
                if (username == null) {
                    return null;  // Si no hay un usuario válido, no procesamos el comando PASS
                }
                return new PassCommand(username, argument);  // Se pasa el nombre de usuario y la contraseña
            } else if (commandClass == GetCommand.class || commandClass == PutCommand.class) {
                // Para comandos que requieren Socket y BufferedReader
                return commandClass.getDeclaredConstructor(Socket.class, BufferedReader.class)
                        .newInstance(clientSocket, input);
            }
            return commandClass.getDeclaredConstructor().newInstance();  // Para comandos sin parámetros
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
