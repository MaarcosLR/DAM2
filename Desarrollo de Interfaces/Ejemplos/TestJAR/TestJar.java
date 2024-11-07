import javax.swing.*;
import ejemplos.RoundButton; // Importar desde el .jar

public class TestJar {
 public static void main(String[] args) {
 JFrame frame = new JFrame("Prueba de Componente JAR");
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setSize(300, 300);
 // Usar el botón redondo desde el .jar
 RoundButton roundButton = new RoundButton("Componente JAR");
 frame.add(roundButton);
 frame.setVisible(true);
 }
}