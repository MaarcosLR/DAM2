package practica1;

import javax.swing.*;
import java.awt.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        //Crear un  nuevo frame
        JFrame calc = new JFrame();

        //Crear un Layout de 6 filas y 4 columnas
        calc.setLayout(new GridLayout(6, 4, 10 ,10));

        //Crear los botones
        JButton porcentaje = new JButton("%");
        JButton ce = new JButton("CE");
        JButton c = new JButton("C");
        JButton borrar = new JButton("⌫");
        JButton unox = new JButton("1/X");
        JButton xdos = new JButton("x^2");
        JButton raiz = new JButton("√");
        JButton dividir = new JButton("÷");
        JButton siete = new JButton("7");
        JButton ocho  = new JButton("8");
        JButton nueve = new JButton("9");
        JButton multiplicacion = new JButton("*");
        JButton cuatro = new JButton("4");
        JButton cinco = new JButton("5");
        JButton seis = new JButton("6");
        JButton resta = new JButton("-");
        JButton uno = new JButton("1");
        JButton dos = new JButton("2");
        JButton tres = new JButton("3");
        JButton suma = new JButton("+");
        JButton masmenos = new JButton("+/-");
        JButton cero = new JButton("0");
        JButton coma = new JButton(",");
        JButton igual = new JButton("=");

        //Añadir los botones al frame creado
        calc.add(porcentaje);
        calc.add(ce);
        calc.add(c);
        calc.add(borrar);
        calc.add(uno);
        calc.add(unox);
        calc.add(xdos);
        calc.add(raiz);
        calc.add(dividir);
        calc.add(siete);
        calc.add(ocho);
        calc.add(nueve);
        calc.add(multiplicacion);
        calc.add(cuatro);
        calc.add(cinco);
        calc.add(seis);
        calc.add(resta);
        calc.add(uno);
        calc.add(dos);
        calc.add(tres);
        calc.add(suma);
        calc.add(masmenos);
        calc.add(cero);
        calc.add(coma);
        calc.add(igual);

        //Establecer el tamaño de la ventana
        calc.setSize(600, 600);

        //Comportamiento de la ventana al cerrar
        calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Mostrar la ventana
        calc.setVisible(true);
    }
}
