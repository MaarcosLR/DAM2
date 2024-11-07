public class ParesImparesDesordenados extends Thread { 
	
    public void run() {
        for (int i = 0; i <= 100; i++)
            if (i % 2 == 0) { 
                System.out.println(i); // Imprime el número par en la consola
            }
    }
    
    public static void main(String[] args) {
        ParesImparesDesordenados pi = new ParesImparesDesordenados();
        pi.start(); // Inicia el hilo, lo que ejecutará el método run en segundo plano
        

        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) { 
                System.out.println(i); // Imprime el número impar en la consola
            }
        }
    }
}
