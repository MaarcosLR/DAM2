package Ej_Museo;

//Implementar un programa en java que simule el siguiente comportamiento:
//
//Se dispone de un museo que es visitado por varios turistas(10).
//El museo solo dispone de un aforo máximo de 5 turistas simultáneos.
//Los accesos al museo se podrán realizar por puertas dobles.
//Existe una sala especial del museo que solo puede ser visitada de 1 en 1.

public class Main {
    public static void main(String[] args) {
        // Crear el museo
        Museo museo = new Museo(5, 1);

        // Crear y lanzar hilos para 10 turistas
        for (int i = 1; i <= 10; i++) {
            new Turista(i, museo).start();
        }
    }
}

//En este caso se utilizan semáforos

//Los semáforos se usan porque permiten controlar cuántos hilos (en este caso, turistas) pueden acceder a un recurso al mismo tiempo:
//
//Aforo del museo: Usamos un semáforo para limitar la entrada a un máximo de 5 turistas. Si el museo está lleno,
//los demás esperan hasta que alguien salga.
//Sala especial: Usamos otro semáforo para garantizar que solo un turista entre a la vez, evitando conflictos.
//
//Son ideales para gestionar acceso concurrente porque automáticamente bloquean y liberan permisos sin que nosotros
// manejemos esa lógica manualmente.

//Semáforos:
//Perfectos para limitar accesos a un recurso compartido con un contador (como el aforo del museo o la exclusividad de la sala especial).
//Automáticamente gestionan permisos (adquirir/liberar) y permiten esperar hasta que el recurso esté disponible.
//Son flexibles porque pueden manejar desde acceso exclusivo (binario) hasta múltiples accesos (contadores).
//
//Bloques synchronized:
//Permiten garantizar exclusión mutua en una sección de código.
//Ejemplo: Podrías usarlos para controlar la sala especial (1 turista a la vez).
//Limitación: No sirven para manejar contadores como el aforo del museo, ya que no pueden controlar múltiples accesos simultáneos.
//
//ReentrantLock:
//Similar a synchronized, pero ofrece más control, como la capacidad de intentar bloquear sin esperar indefinidamente.
//También sería útil para la sala especial, pero no maneja un límite de accesos como el aforo del museo.
//Ventaja: Se puede liberar el bloqueo manualmente en cualquier parte del código.
//Desventaja: Más complejo y requiere cuidado para evitar olvidos al liberar.
//
//Atomic Variables:
//Variables como AtomicInteger pueden ser útiles para contadores, como rastrear cuántos turistas están dentro del museo.
//Limitación: No bloquean automáticamente hilos cuando el contador llega al límite, por lo que tendrías que implementar
// manualmente una lógica para esperar y notificar.

//¿Por qué Semáforos?
//En este caso, los semáforos son ideales porque:
//
//Gestionan automáticamente el acceso múltiple (aforo) o exclusivo (sala especial).
//Son más simples de implementar para este escenario que las otras opciones.
//Evitan escribir lógica adicional para bloquear y liberar recursos.