package Practica_1;

public class ParesImparesOrdenados extends Thread { // Se define la clase que extiende de Thread

    // Creo los hilos
    private static Thread Pares; 
    private static Thread Impares;
    
    // Empiezo primero el hilo de los pares y luego el de los impares
    public static void main(String[] args) {
        Pares = new Thread() {
            public void run() { // Método que se ejecuta al iniciar el hilo
                for (int i = 0; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i); // Imprime el número par en la consola
                    }
                }
            }
        };
        Pares.start(); // Inicia el hilo de los pares
        
        System.out.println("");

        Impares = new Thread() { 
            public void run() { // Método que se ejecuta al iniciar el hilo
                for (int i = 0; i <= 100; i++) { // Bucle que recorre los números del 0 al 100
                    if (i % 2 != 0) { // Verifica si el número es impar
                        System.out.println(i); // Imprime el número impar en la consola
                    }
                }
            }
        };
        Impares.start(); // Inicia el hilo de los impares
    }
}
