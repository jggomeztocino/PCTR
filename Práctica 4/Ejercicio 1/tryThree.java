/**
 * Tercer intento del algoritmo de Dekker.
 * @author Jesús Gómez
 * @version 1.0
 * @see Thread
 */
public class tryThree extends Thread {
    private int tipoHilo; // Variable que almacena el tipo de hilo / proceso.

    /**
     * Variables que pretenden indicar cuando un proceso está en su sección crítica.
     */
    private static volatile boolean wantp = false;
    private static volatile boolean wantq = false;

    /**
     * Constructor de clase
     * @param tipoHilo Tipo de proceso a ejecutar, p(1) o q(2)
     */
    public tryThree(int tipoHilo) { this.tipoHilo=tipoHilo; }

    /**
     * Método heredado de Thread,
     * que definirá el comportamiento del hilo.
     * @see Thread
     */
    public void run(){
        switch (tipoHilo) {
            case 1 -> { // Proceso p
                while (true) { // loop forever
                    // non-critical section
                    wantp = true; // wantp ← true
                    while (wantq) ; // await wantq = false
                    System.out.println(this.getName()); // critical section
                    wantp = false; // wantp ← false
                }
            }
            case 2 -> { // Proceso q
                while (true) { // loop forever
                    // non-critical section
                    wantq = true; // wantq ← true
                    while (wantp) ; // await wantp = false
                    System.out.println(this.getName()); // critical section
                    wantq = false; // wantq ← false
                }
            }
        }
    }

    /**
     * Hilo principal donde se lanzarán los 2 hilos
     * que demostrarán el comportamiento del tercer intento
     * del algoritmo de Dekker.
     * @param args No se usa
     * @throws InterruptedException Producida por los métodos join()
     */
    public static void main(String[] args) throws InterruptedException{
        tryThree t1 = new tryThree(1); // Proceso p
        tryThree t2 = new tryThree(2); // Proceso q
        t1.start(); t2.start();
        t1.join(); t2.join();
    }
}
