import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Implementación del algoritmo de Peterson.
 * @author Jesús Gómez
 * @version 1.0
 * @see Thread
 * @see Runnable
 */
public class algPeterson implements Runnable {
    /**
     * Variable que almacena el tipo de hilo / proceso.
     */
    private int tipoHilo;

    /**
     * Variables que pretenden indicar cuando un proceso está en su sección crítica.
     */
    private static volatile boolean wantp = false;
    private static volatile boolean wantq = false;
    /**
     * Variable flag.
     */
    private static volatile int last = 1;


    /**
     * Constructor de clase
     * @param tipoHilo Tipo de proceso a ejecutar, p(1) o q(2)
     */
    public algPeterson(int tipoHilo) { this.tipoHilo=tipoHilo; }

    /**
     * Método heredado de Thread e implementado por Runnable,
     * que definirá el comportamiento del hilo.
     * @see Thread
     * @see Runnable
     */
    public void run(){
        switch (tipoHilo) {
            case 1 -> { // Proceso p
                while (true) { // loop forever
                    // non-critical section
                    wantp = true; // wantp ← true
                    last = 1; // last ← 1
                    while(wantq && last !=2); // await wantq = false or last = 2
                    System.out.println(Thread.currentThread().getName()); // critical section
                    wantp = false; // wantp ← false
                }
            }
            case 2 -> { // Proceso q
                while (true) { // loop forever
                    wantq = true; // wantq ← true
                    last = 2; // last ← 2
                    while(wantp && last !=1); // await wantp = false or last = 1
                    System.out.println(Thread.currentThread().getName()); // critical section
                    wantq = false; // wantq ← false
                }
            }
        }
    }
    /**
     * Hilo principal donde se lanzarán los 2 hilos
     * que demostrarán el comportamiento del algoritmo de Peterson.
     * @param args No se usa
     * @throws InterruptedException Producida por los métodos join()
     */
    public static void main(String[] args) throws InterruptedException{
        Runnable r1 = new algPeterson(1); // Proceso p
        Runnable r2 = new algPeterson(2); // Proceso q
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(r1);
        executorService.execute(r2);
        executorService.shutdown();
        while(!executorService.isTerminated());
    }
}