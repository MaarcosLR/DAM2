package UT3_Practica;

import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClienteUDP {

    private static final Integer PORT = 5555;
    private static final String SERVER_IP = "localhost";
    private static final String filePath = "C:\\Users\\falco\\Desktop\\pruebaPSPTXT\\ao.txt";
    private static final int CHUNK_SIZE = 512; // Tamaño de cada bloque (512 bytes)

    public static void main(String[] args) {
        try (DatagramSocket ds = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(SERVER_IP);

            // Leer el archivo como un arreglo de bytes
            Path path = Paths.get(filePath);
            byte[] fileContent = Files.readAllBytes(path);

            // Dividir el archivo en bloques y enviarlos
            int totalChunks = (int) Math.ceil((double) fileContent.length / CHUNK_SIZE);
            System.out.println("Enviando " + totalChunks + " bloques...");

            for (int i = 0; i < totalChunks; i++) {
                int offset = i * CHUNK_SIZE;
                int length = Math.min(CHUNK_SIZE, fileContent.length - offset);
                byte[] chunk = new byte[length + 4]; // +4 para el número de bloque
                System.arraycopy(fileContent, offset, chunk, 4, length);

                // Agregar el número de bloque al inicio del chunk
                chunk[0] = (byte) (i >> 24);
                chunk[1] = (byte) (i >> 16);
                chunk[2] = (byte) (i >> 8);
                chunk[3] = (byte) i;

                // Enviar el bloque al servidor
                DatagramPacket dp = new DatagramPacket(chunk, chunk.length, address, PORT);
                ds.send(dp);

                // Esperar confirmación del servidor
                byte[] ackBuffer = new byte[4];
                DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
                ds.setSoTimeout(3000); // Timeout de 3 segundos

                boolean ackReceived = false;
                while (!ackReceived) {
                    try {
                        ds.receive(ackPacket);
                        int ackChunkNumber = (ackBuffer[0] << 24) | (ackBuffer[1] << 16) | (ackBuffer[2] << 8) | ackBuffer[3];
                        if (ackChunkNumber == i) {
                            System.out.println("Confirmación recibida para el bloque: " + i);
                            ackReceived = true;
                        }
                    } catch (SocketTimeoutException e) {
                        System.out.println("Timeout, reenviando bloque: " + i);
                        ds.send(dp); // Reenviar el bloque
                    }
                }
            }

            // Enviar mensaje de finalización
            byte[] finMessage = "FIN".getBytes();
            DatagramPacket finPacket = new DatagramPacket(finMessage, finMessage.length, address, PORT);
            ds.send(finPacket);
            System.out.println("Transferencia completada.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}