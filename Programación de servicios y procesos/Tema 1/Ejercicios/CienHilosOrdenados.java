public class CienHilosOrdenados extends Thread {
    private int numero; 

    public CienHilosOrdenados(int numero) {
        this.numero = numero; 
    }


    public void run() {
        System.out.println("Hilo número: " + numero); // Imprime el número del hilo en la consola
    }
    
    public static void main(String[] args) throws InterruptedException {
    	
        // Bucle que crea e inicia 100 hilos
        for (int i = 1; i <= 100; i++) {
            CienHilosOrdenados ch = new CienHilosOrdenados(i); 
            ch.start(); // Inicia el hilo
            ch.join(); // Espera a que el hilo termine antes de continuar al siguiente
        }
    }
}
