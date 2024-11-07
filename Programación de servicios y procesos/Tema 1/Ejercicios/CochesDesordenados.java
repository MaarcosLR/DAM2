public class CochesDesordenados {
    public static void main(String[] args) throws InterruptedException {
        String[] pilotos = {"Hamilton", "Vettel", "Raikkonen", "Alonso", "Sainz Jr", "Bottas", "Vandoorne"};

        class Coche implements Runnable {
            private String piloto;
            
            public Coche(String piloto) {
                this.piloto = piloto;
            }

            @Override
            public void run() {
                for(int i = 0; i < 78; i++) {
                    System.out.println(piloto + " ha completado la vuelta " + (i + 1));
                }
                System.out.println(piloto + " ha terminado la carrera.");
            }
        }

        // Crear un hilo para cada piloto
        Thread[] hilo = new Thread[pilotos.length];

        // Iniciar los hilos y hacer que cuenten las vueltas en orden con join()
        for (int i = 0; i < pilotos.length; i++) {
            hilo[i] = new Thread(new Coche(pilotos[i]));
            hilo[i].start();
        }
    }
}
