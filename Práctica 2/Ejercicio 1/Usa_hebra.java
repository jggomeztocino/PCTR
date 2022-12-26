/**
 *                                  Clase Usa_hebra
 * Clase que contiene el hilo principal y demuestra el funcionamiento de la clase hebra.java
 * @author Jesús Gómez Tocino
 * @version 1.0
 * @see hebra
 */

public class Usa_hebra {

    /**
     * Hilo principal
     * @param args (No se usa)
     */
    public static void main(String[] args){
        hebra h1 = new hebra(1000000); // Creamos el primer hilo, con el tipo implícito en el signo del número de iteraciones
        hebra h2 = new hebra(-1000000); // Creamos el segundo hilo, con el tipo implícito en el signo del número de iteraciones

        /**
         * Lanzamiento de los hilos
         * @see Thread
         */
        h1.start();
        h2.start();

        /**
         * Sección crítica del programa concurrente.
         * Se controlan las posibles excepciones lanzadas por los métodos join().
         * @exception InterruptedException
         * @see InterruptedException
         */
        try{ // Bloque de captura de excepciones

            h1.join(); // Pausamos la ejecución del hilo principal hasta que el hilo 'h1' termine
            h2.join(); // Pausamos la ejecución del hilo principal hasta que el hilo 'h2' termine

        }catch(InterruptedException i){ // Capturamos la excepción

            System.out.println("Excepcion capturada.");

        }

        System.out.println("Valor n final: " + h1.getN()); // Imprimimos el valor final de la variable estática 'n' mediante el método observador "getN"

    }
}
