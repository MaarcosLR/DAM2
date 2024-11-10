
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

public class Ejercicio3 {
    public static void main(String[] args) {

    	//Crear el JFrame llamado reproductor
        JFrame reproductor = new JFrame();
        reproductor.setTitle("Ejercicio 3");

        //Crear el JPanel en el que irán los botones
        JPanel botones = new JPanel();

        //Establecemos el Layout como GridLayout de 3 filas y 3 columnas
        botones.setLayout(new GridLayout(3, 3));

        //Crear los botones
        JButton boton = new JButton("🔁");
        JButton boton2 = new JButton("⏹");
        JButton boton3 = new JButton("🔀");
        JButton boton4 = new JButton("⏮");
        JButton boton5 = new JButton("▶");
        JButton boton6 = new JButton("⏭");
        JButton boton7 = new JButton("⏪");
        JButton boton8 = new JButton("⏸");
        JButton boton9 = new JButton("⏩");

        //Añadir los botones
        botones.add(boton);
        botones.add(boton2);
        botones.add(boton3);
        botones.add(boton4);
        botones.add(boton5);
        botones.add(boton6);
        botones.add(boton7);
        botones.add(boton8);
        botones.add(boton9);
        
        //Añadir el JPanel al JFrame
        reproductor.add(botones);

        //Añadir un tamaño mínimo para que se abra desde el principio, si no, solo se abre lo q ocupen los botones
        reproductor.setMinimumSize(new Dimension(300,150));
        
        //Empaquetarlos para no tener que establecer una tamaño de ventana X
        reproductor.pack();

        //Comportamiento de la ventana al cerrar
        reproductor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Hacer visible la ventana
        reproductor.setVisible(true);
    }
}
