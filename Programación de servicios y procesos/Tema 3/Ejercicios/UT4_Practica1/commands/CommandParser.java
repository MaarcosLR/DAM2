package UT4_Practica1.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private final Map<String, Class<? extends FTPCommand>> commandMap = new HashMap<>();
    private String username;  // Variable para almacenar el nombre de usuario

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

    public FTPCommand getCommand(String commandName, String argument) {
        Class<? extends FTPCommand> commandClass = commandMap.get(commandName.toUpperCase());
        if (commandClass == null) {
            return null;
        }

        try {
            if (commandClass == UserCommand.class) {
                // Para el comando USER, pasamos solo el argumento (nombre de usuario)
                return new UserCommand(argument);
            } else if (commandClass == PassCommand.class) {
                // Para el comando PASS, pasamos tanto el nombre de usuario como la contraseña
                if (username == null) {
                    return null;  // Si no hay un usuario validado previamente, no procesamos el comando PASS
                }
                return new PassCommand(username, argument);  // Pasamos el nombre de usuario y la contraseña
            }
            // Para otros comandos sin parámetros
            return commandClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para establecer el nombre de usuario (esto se hace en el comando USER)
    public void setUsername(String username) {
        this.username = username;
    }

    // Método para obtener el nombre de usuario
    public String getUsername() {
        return username;
    }
}
