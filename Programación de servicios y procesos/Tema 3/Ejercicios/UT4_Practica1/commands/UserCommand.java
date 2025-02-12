package UT4_Practica1.commands;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserCommand implements FTPCommand {
    private static final Map<String, String> USERS = new HashMap<>();

    static {
        // Definimos varios usuarios con sus contraseñas
        USERS.put("admin", "admin");
        USERS.put("user1", "password1");
        USERS.put("user2", "password2");
    }

    private String username;

    public UserCommand(String argument) {
        this.username = argument;  // El nombre de usuario ingresado
    }

    @Override
    public void execute(String[] args, PrintWriter salida) {
        if (username == null || username.isEmpty()) {
            salida.println("ERROR: Debes proporcionar un nombre de usuario.");
            return;
        }

        // Verificamos si el nombre de usuario existe en el conjunto predefinido
        if (USERS.containsKey(username)) {
            salida.println("Usuario " + username + " recibido. Introduce PASS.");
        } else {
            salida.println("ERROR: Usuario incorrecto.");
        }
    }

    // Getter para obtener el nombre de usuario
    public String getUsername() {
        return username;
    }
}
