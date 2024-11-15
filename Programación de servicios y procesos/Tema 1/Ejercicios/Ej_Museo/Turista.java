package Ej_Museo;

public class Turista extends Thread {

    private final int num;
    private final Museo museo;

    public Turista(int num, Museo museo) {
        this.num = num;
        this.museo = museo;
    }

    @Override
    public void run() {
        try {
            // Intentar entrar al museo
            museo.entrarMuseo(num);

            // Simular tiempo dentro del museo
            Thread.sleep((long) (Math.random() * 3000) + 1000);

            // Intentar entrar a la sala especial
            museo.entrarSalaEspecial(num);

            // Simular tiempo en la sala especial
            Thread.sleep((long) (Math.random() * 2000) + 500);

            // Salir de la sala especial
            museo.salirSalaEspecial(num);

            // Salir del museo
            museo.salirMuseo(num);

        } catch (InterruptedException e) {
            System.err.println("Turista " + num + " fue interrumpido.");
        }
    }
}
