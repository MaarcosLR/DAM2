package Practica1EjercicioBasico;


import java.io.IOException;

public class MainServer {

    public static void main(String[] args) throws IOException {
        Servidor servidor = null;
        try {
            servidor = new Servidor();
        } catch (IOException ex){
            System.err.println("No se ha podido inicializar el servidor.");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            servidor.waitConnections();
        } catch (IOException ex){
            System.err.println("No se ha podido inicializar la comunicación con el cliente.");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

    }
}
