/**
 * Versión final del algoritmo de Dekker.
 * @author Jesús Gómez
 * @version 1.0
 * @see Thread
 */
public class algDekker extends Thread{
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
     * Variable que indicará de cuál proceso es el turno.
     */
    private static volatile int turn = 1;

    /**
     * Constructor de clase
     * @param tipoHilo Tipo de proceso a ejecutar, p(1) o q(2)
     */
    public algDekker(int tipoHilo) { this.tipoHilo=tipoHilo; }

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
                    while (wantq) { // while wantq
                        if (turn == 2) { // if turn = 2
                            wantp = false; // wantp ← false
                            while (turn != 1) ; // await turn = 1
                            wantp = true; // wantp ← true
                        }
                    }
                    System.out.println(this.getName()); // critical section
                    turn = 2; // turn ← 2
                    wantp = false; // wantp ← false
                }
            }
            case 2 -> { // Proceso q
                while (true) { // loop forever
                    // non-critical section
                    wantq = true; // wantq ← true
                    while (wantp) { // while wantp
                        if (turn == 1) { // if turn = 1
                            wantq = false; // wantq ← false
                            while (turn != 2) ; // await turn = 2
                            wantq = true; // wantq ← true
                        }
                    }
                    System.out.println(this.getName()); // critical section
                    turn = 1; // turn ← 1
                    wantq = false; // wantq ← false
                }
            }
        }
    }

    /**
     * Hilo principal donde se lanzarán los 2 hilos
     * que demostrarán el comportamiento del algoritmo de Dekker.
     * @param args No se usa
     * @throws InterruptedException Producida por los métodos join()
     */
    public static void main(String[] args) throws InterruptedException{
        algDekker t1 = new algDekker(1); // Proceso p
        algDekker t2 = new algDekker(2); // Proceso q
        t1.start(); t2.start();
        t1.join(); t2.join();
    }
}
