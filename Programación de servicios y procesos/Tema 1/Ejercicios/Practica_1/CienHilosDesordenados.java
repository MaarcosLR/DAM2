package Practica_1;

public class CienHilosDesordenados extends Thread {
    private int numero;

    // Constructor que recibe el número del hilo
    public CienHilosDesordenados(int numero) {
        this.numero = numero; // Asigna el número al hilo
    }

    // Método que se ejecuta al iniciar el hilo
    public void run() {
        System.out.println("Hilo número: " + numero);
    }
    
    public static void main(String[] args){ 
    	
        for (int i = 1; i <= 100; i++) {
            CienHilosDesordenados ch = new CienHilosDesordenados(i);
            ch.start(); // Inicia el hilo
        }
    }
}
