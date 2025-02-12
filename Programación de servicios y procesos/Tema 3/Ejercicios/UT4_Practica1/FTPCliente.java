package UT4_Practica1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FTPCliente {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public FTPCliente(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);  // Conexión al servidor con el puerto actualizado
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            // Leer la respuesta inicial del servidor
            String response = reader.readLine();
            System.out.println(response);

            while (true) {
                // Leer el comando del cliente
                System.out.print("ftp> ");
                String command = scanner.nextLine();
                writer.println(command);

                // Si el comando es QUIT, cerramos la conexión
                if (command.equalsIgnoreCase("QUIT")) {
                    break;
                }

                // Leer y mostrar la respuesta del servidor
                String serverResponse;
                while ((serverResponse = reader.readLine()) != null) {
                    if (serverResponse.isEmpty()) break;
                    System.out.println(serverResponse);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (socket != null) socket.close();
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Cambia el puerto a 2121 (igual que el servidor)
            FTPCliente client = new FTPCliente("localhost", 2121);
            client.start();
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}
