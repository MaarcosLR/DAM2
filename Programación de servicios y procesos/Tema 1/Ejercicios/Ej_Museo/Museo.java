package Ej_Museo;

import java.util.concurrent.Semaphore;

public class Museo {

    private final Semaphore aforoMuseo;  // Aforo general
    private final Semaphore salaEspecial; // Aforo sala especial

    public Museo(int aforoMaximo, int capacidadSalaEspecial) {
        this.aforoMuseo = new Semaphore(aforoMaximo);
        this.salaEspecial = new Semaphore(capacidadSalaEspecial);
    }

    // Entrar al museo
    public void entrarMuseo(int numTurista) throws InterruptedException {
        System.out.println("Turista " + numTurista + " quiere entrar al museo.");
        aforoMuseo.acquire();
        System.out.println("Turista " + numTurista + " ha entrado al museo.");
    }

    // Salir del museo
    public void salirMuseo(int numTurista) {
        System.out.println("Turista " + numTurista + " ha salido del museo.");
        aforoMuseo.release();
    }

    // Intentar entrar a la sala especial
    public void entrarSalaEspecial(int numTurista) throws InterruptedException {
        System.out.println("Turista " + numTurista + " quiere entrar a la sala especial.");
        salaEspecial.acquire();
        System.out.println("Turista " + numTurista + " está en la sala especial.");
    }

    // Salir de la sala especial
    public void salirSalaEspecial(int numTurista) {
        System.out.println("Turista " + numTurista + " ha salido de la sala especial.");
        salaEspecial.release();
    }
}
