package Practica1EjercicioBasico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket serverSocket;
    private final String HOST = "localhost";
    private final int PORT = 54321;
    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;

    public Servidor() throws IOException {
        System.out.println("Iniciando Servidor en el PORT: " + PORT);
        this.serverSocket = new ServerSocket(PORT);
    }

    public void waitConnections() throws IOException{
        System.out.println("Esperar conexiones de clientes");
        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado correctamente");
        System.out.println("Enviar y recibir mensajes del cliente");
        recibirDatos = new DataInputStream(socket.getInputStream());
        enviarDatos = new DataOutputStream(socket.getOutputStream());
        writeUTF("Bienvenido a tu servidor favorito.");
        readUTF();

        System.out.println("Adiós!");
    }

    private void readUTF() throws IOException {
        try {
            System.out.println(Colors.ANSI_RED+"CLIENT: "+recibirDatos.readUTF()+ Colors.ANSI_RESET);
        } catch (IOException ex){
            System.err.println("Error al leer el recibir mensajes del cliente");
        }
    }

    private void writeUTF(String mensaje) throws IOException {
        try {
            enviarDatos.writeUTF(mensaje);
        } catch (IOException ex){
            System.err.println("Error al escribir mensajes al cliente");
        }
    }
}
