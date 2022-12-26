/**
 *                                  Clase Usa_tareaRunnable
 * Clase que contiene el hilo principal y demuestra el funcionamiento de la clase tareaRunnable.java
 * @author Jesús Gómez Tocino
 * @version 1.0
 * @see tareaRunnable
 * @see Runnable
 * @see Thread
 */

public class Usa_tareaRunnable {
    /**
     * Hilo principal
     * @param args (No se usa)
     */
    public static void main (String[] args){
        /**
         * Creación de los objetos Runnable,
         * mediante conversión explícita desde la clase tareaRunnable
         * @see Runnable
         */
        Runnable r1 = new tareaRunnable(1000000);
        Runnable r2 = new tareaRunnable(-1000000);

        /**
         * Creación de los hilos a lanzar, mediante el constructor Thread(Runnable target)
         * @see Thread
         */
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        /**
         * Lanzamiento de los hilos creados
         */
        t1.start();
        t2.start();

        /**
         * Sección crítica del programa concurrente.
         * Se controlan las posibles excepciones lanzadas por los métodos join().
         * @exception InterruptedException
         * @see InterruptedException
         */
        try{ // Bloque de captura de excepciones

            t1.join(); // Pausamos la ejecución del hilo principal hasta que el hilo 't1' termine
            t2.join(); // Pausamos la ejecución del hilo principal hasta que el hilo 't2' termine

        }catch(InterruptedException i){ // Capturamos la excepción

            System.out.println("Excepcion capturada.");

        }

        System.out.println("Valor n final: " + ((tareaRunnable) r1).getN()); /* Mediante la resolución de ámbito,
        imprimimos el valor final de la variable estática 'n' a través del método observador "getN" */

    }
}
