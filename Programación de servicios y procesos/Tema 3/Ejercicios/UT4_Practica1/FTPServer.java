package UT4_Practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class FTPServer {
    private static final int PORT = 2121;  // Puerto actualizado
    private static final Map<String, String> usuarios = new HashMap<String, String>();

    public static void main(String[] args) {
        // Agregar usuarios
        usuarios.put("admin", "admin");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor FTP iniciado.");

            while (true) {
                // Espera una nueva conexión del cliente
                Socket socketCliente = serverSocket.accept();
                System.out.println("Cliente conectado: " + socketCliente.getRemoteSocketAddress());

                // Crea y arranca un hilo para manejar la conexión del cliente
                HiloCliente hiloCliente = new HiloCliente(socketCliente);
                new Thread(hiloCliente).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase que maneja la conexión con el cliente
    static class HiloCliente implements Runnable {
        private Socket socket;
        private BufferedReader entrada;
        private PrintWriter salida;
        private boolean autenticado = false;
        private String username = null;
        private String password = null;

        public HiloCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                // Mensaje de bienvenida
                salida.println("Bienvenido al Servidor FTP. Use USER <nombre> y PASS <contraseña>");

                // Recibir el comando USER
                String line = entrada.readLine();
                if (line != null && line.startsWith("USER")) {
                    String[] parts = line.split(" ", 2);
                    username = parts[1];
                    salida.println("Usuario: '" + username + "' encontrado.");
                } else {
                    salida.println("Usuario no encontrado.");
                    socket.close();
                    return;
                }

                // Ahora, recibir el comando PASS
                line = entrada.readLine();
                if (line != null && line.startsWith("PASS")) {
                    String[] parts = line.split(" ", 2);
                    password = parts[1];

                    // Verificar la contraseña
                    if (password.equals(usuarios.get(username))) {
                        autenticado = true;
                        salida.println("Contraseña aceptada.");
                    } else {
                        salida.println("Contraseña incorrecta.");
                        socket.close();
                        return;
                    }
                } else {
                    salida.println("Contraseña no proporcionada.");
                    socket.close();
                    return;
                }

                // Después de la autenticación, mantener la conexión abierta
                while (autenticado) {
                    line = entrada.readLine();  // Leer comandos del cliente
                    if (line != null) {
                        if (line.equalsIgnoreCase("QUIT")) {
                            salida.println("Conexión cerrada.");
                            socket.close();
                            break;
                        } else {
                            // Responder con el comando recibido (esto puede ser expandido con más comandos)
                            salida.println("Comando recibido: " + line);
                        }
                    }
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
