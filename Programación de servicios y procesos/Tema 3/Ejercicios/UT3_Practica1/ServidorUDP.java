package UT3_Practica1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ServidorUDP {

    private static final Integer PORT = 5555;
    private static final String OUTPUT_PATH = "C:\\Users\\falco\\Desktop\\pruebaPSPTXT\\archivo_recibido.txt";

    public static void main(String[] args) {
        try (DatagramSocket ds = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP escuchando el puerto: " + PORT);

            Map<Integer, byte[]> receivedChunks = new HashMap<>();
            boolean transferComplete = false;

            while (!transferComplete) {
                // Recibir un bloque
                byte[] buffer = new byte[512 + 4]; // +4 para el número de bloque
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                ds.receive(dp);

                // Verificar si es el mensaje de finalización
                String message = new String(dp.getData(), 0, dp.getLength());
                if (message.equals("FIN")) {
                    transferComplete = true;
                    System.out.println("Transferencia completada.");
                    break;
                }

                // Extraer el número de bloque
                int chunkNumber = (buffer[0] << 24) | (buffer[1] << 16) | (buffer[2] << 8) | buffer[3];
                System.out.println("Bloque recibido: " + chunkNumber);

                // Guardar el bloque en el mapa
                byte[] chunkData = new byte[dp.getLength() - 4];
                System.arraycopy(buffer, 4, chunkData, 0, dp.getLength() - 4);
                receivedChunks.put(chunkNumber, chunkData);

                // Enviar confirmación al cliente
                byte[] ackBuffer = new byte[4];
                ackBuffer[0] = (byte) (chunkNumber >> 24);
                ackBuffer[1] = (byte) (chunkNumber >> 16);
                ackBuffer[2] = (byte) (chunkNumber >> 8);
                ackBuffer[3] = (byte) chunkNumber;
                DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length, dp.getAddress(), dp.getPort());
                ds.send(ackPacket);
            }

            // Reconstruir el archivo
            byte[] fileContent = new byte[receivedChunks.size() * 512];
            int offset = 0;
            for (int i = 0; i < receivedChunks.size(); i++) {
                byte[] chunk = receivedChunks.get(i);
                System.arraycopy(chunk, 0, fileContent, offset, chunk.length);
                offset += chunk.length;
            }

            // Guardar el archivo en disco
            Path outputPath = Paths.get(OUTPUT_PATH);
            Files.write(outputPath, fileContent);
            System.out.println("Archivo guardado en: " + OUTPUT_PATH);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}