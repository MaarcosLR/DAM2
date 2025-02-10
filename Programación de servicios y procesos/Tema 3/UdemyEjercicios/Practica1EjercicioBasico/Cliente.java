package Practica1EjercicioBasico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    private final String HOST;
    private final int PORT;
    private final Socket socket;
    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;

    public Cliente(String HOST, int PORT) throws IOException {
        this.HOST = HOST;
        this.PORT = PORT;
        System.out.println("Iniciando Socket contra el HOST: " + HOST + " y PORT: " + PORT);
        socket = new Socket(HOST,PORT);
    }

    public void connect() throws IOException {
        System.out.println("Enviar y recibir mensajes del server");
        recibirDatos = new DataInputStream(socket.getInputStream());
        enviarDatos = new DataOutputStream(socket.getOutputStream());
        readUTF();
        writeUTF("Muchas gracias");

        System.out.println("Adiós!");
    }

    private void readUTF() throws IOException {
        try {
            System.out.println(Colors.ANSI_GREEN+"SERVER: "+recibirDatos.readUTF()+ Colors.ANSI_RESET);
        } catch (IOException ex){
            System.err.println("Error al leer el recibir mensajes del server");
        }
    }

    private void writeUTF(String mensaje) throws IOException {
        try {
            enviarDatos.writeUTF(mensaje);
        } catch (IOException ex){
            System.err.println("Error al escribir mensajes al server");
        }
    }

}
