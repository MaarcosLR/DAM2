import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class claseRuntime {
    public static void main(String[] args) {
        String comando = "ls -a";
//        String comando = "ls -javier" //comando erróneo para que me salga como en la terminal
//        String comando = "pato" //comando inexistente en linux

        try {
            // Ejecutar el comando
            Process process = Runtime.getRuntime().exec(comando);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("Error al ejecutar el comando:");
                System.out.println(errorOutput.toString());
            } else {
                System.out.println("Salida del comando:");
                System.out.println(output.toString());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
