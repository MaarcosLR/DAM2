package Practica_1_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1 {

    public static void main(String[] args) {
        try {
            // Crear el proceso para listar los procesos activos con "tasklist" en Windows
            ProcessBuilder processBuilder = new ProcessBuilder("tasklist");
            Process proceso = processBuilder.start();

            // Lectura de la salida del proceso en ejecución
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Salida del proceso: " + linea);
            }

            // Monitoreo del estado del proceso
            while (proceso.isAlive()) {
                System.out.println("El proceso sigue en ejecución...");
                Thread.sleep(1000); // Espera 1 segundo antes de verificar de nuevo
            }

            // Finalizar el monitoreo cuando el proceso termina
            int exitCode = proceso.waitFor(); // Espera a que el proceso termine
            System.out.println("El proceso ha terminado.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
