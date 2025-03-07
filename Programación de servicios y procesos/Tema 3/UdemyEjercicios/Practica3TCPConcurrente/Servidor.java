package Practica3TCPConcurrente;

import Practica1EjercicioBasico.Colors;

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

    public void waitConnections() throws IOException {
        System.out.println("Esperar conexiones de clientes");
        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        recibirDatos = new DataInputStream(socket.getInputStream());
        enviarDatos = new DataOutputStream(socket.getOutputStream());
        writeUTF("Bienvenido a tu calculadora favorita.");
        writeUTF("Porfavor selecciona una opción: ");
        int opcion = -1;
        while (opcion!=0) {
            writeUTF("\n\t1.-SUMAR\n\t2.-RESTAR\n\t3.-MULTIPLICAR\n\t4.-DIVIDIR\n\t5.-MÓDULO\n\t0.-SALIR");
            opcion = readInt();
            switch (opcion) {
                /**
                 * SUMAR
                 * RESTAR
                 * MULTIPLICAR
                 * DIVIDIR
                 * MÓDULO
                 * SALIR
                 */
                case 0:
                    writeUTF("Muchas gracias por utilizar las aplicación");
                    System.out.println("Fin de la aplicación");
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    operacion(opcion);
                    break;
                default:
                    writeUTF("Opción no valida");

            }

        }

    }

    private void readUTF() throws IOException {
        try {
            System.out.println(Colors.ANSI_RED + "CLIENT: " + recibirDatos.readUTF() + Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer el recibir mensajes del cliente");
        }
    }

    private void writeUTF(String mensaje) throws IOException {
        try {
            enviarDatos.writeUTF(mensaje);
        } catch (IOException ex) {
            System.err.println("Error al escribir mensajes al cliente");
        }
    }

    private int readInt() {
        int num = 0;
        try {
            num = recibirDatos.readInt();
            System.out.println(Colors.ANSI_RED + "CLIENT: " + num + Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer el mensaje del cliente");
        }
        return num;
    }

    private void operacion(int opcion) throws IOException {
        writeUTF("Porfavor introduce un número: ");
        int num1 = readInt();
        writeUTF("Introduce otro número");
        int num2 = readInt();
        switch (opcion) {
            case 1:
                writeUTF("El resultado de la suma es: " + (num1 + num2));
                break;
            case 2:
                writeUTF("El resultado de la resta es: " + (num1 - num2));
                break;
            case 3:
                writeUTF("El resultado de la multiplicación es: " + (num1 * num2));
                break;
            case 4:
                writeUTF("El resultado de la división es: " + (num1 / num2));
                break;
            case 5:
                writeUTF("El resultado del módulo es: " + (num1 % num2));
                break;
            default:
                writeUTF("Opción incorrecta");
        }
    }
}
