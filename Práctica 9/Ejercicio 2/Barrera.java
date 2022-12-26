import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**                                     Barrera
 * Hilo principal donde se crean tres hilos y los hace esperar en una barrera utilizando la clase CyclicBarrier.
 * @author Jesús Gómez
 * @version 1.0
 * @see CyclicBarrier
 */
public class Barrera {
    public static void main(String[] args) {
        // Creamos la barrera con un límite de 3 hilos
        CyclicBarrier barrera = new CyclicBarrier(3);

        // Creamos y arrancamos los tres hilos
        new Thread(new myThread(barrera)).start();
        new Thread(new myThread( barrera)).start();
        new Thread(new myThread( barrera)).start();
    }
}

/**
 * Clase que implementa la interfaz Runnable y recibe como parámetro una barrera CyclicBarrier en su constructor.
 * Una vez que todos los hilos han llegado a la barrera, continúan con su ejecución.
 */
class myThread implements Runnable {
    private CyclicBarrier barrera; // Barrera a la que deben llegar los hilos

    /**
     * Constructor de la clase.
     * @param barrera Barrera a la que deben llegar los hilos.
     */
    public myThread(CyclicBarrier barrera) {
        this.barrera = barrera;
    }

    @Override
    public void run() {
        // Realizamos una tarea
        System.out.println(Thread.currentThread().getName() + " realizando tarea...");

        try {
            // Esperamos a que los demás hilos lleguen a la barrera
            barrera.await();
        } catch (InterruptedException ignore) {
        } catch (BrokenBarrierException ignore) {}

        // Continuamos con la ejecución una vez que todos los hilos hayan llegado a la barrera
        System.out.println(Thread.currentThread().getName() + " continuando ejecución...");
    }
}
