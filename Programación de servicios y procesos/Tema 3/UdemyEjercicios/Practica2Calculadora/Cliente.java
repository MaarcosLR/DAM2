package Practica2Calculadora;

import Practica1EjercicioBasico.Colors;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

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
        recibirDatos = new DataInputStream(socket.getInputStream());
        enviarDatos = new DataOutputStream(socket.getOutputStream());
        readUTF();
        readUTF();
        while (true){
            readUTF();
            writeInt();
            readUTF();
            readUTF();
            writeInt();
            readUTF();
            writeInt();
            readUTF();
        }
    }

    private void readUTF() throws IOException {
        try {
            System.out.println(Colors.ANSI_GREEN+"SERVER: "+recibirDatos.readUTF()+Colors.ANSI_RESET);
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

    private void writeInt() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        try {
            enviarDatos.writeInt(num);
        } catch (IOException e) {
            System.err.println("Error al enviar datos al servidor.");
            throw new RuntimeException(e);
        }
    }

}
