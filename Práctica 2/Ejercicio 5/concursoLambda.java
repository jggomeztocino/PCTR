/**
 *                                    concursoLambda
 * Clase concursoLambda, que contiene el hilo principal.
 * Se crea una condición de concurso entre dos hilos que acceden a un mismo dato.
 * Los objetos Runnable son creados mediantes funciones anónimas (Lambda)
 * @author Jesús Gómez
 * @version 1.0
 * @see Runnable
 * @see Thread
 */

public class concursoLambda {

    static public int n = 0; // Atributo compartido por todas las instancias de la clase

    /**
     * Hilo principal
     * @param args (No se usa)
     */
    public static void main (String [] args){
        /**
         * Creación de los objetos Runnable mediante una función lambda
         * @see Runnable
         */
        Runnable r1 = () ->{ n++; };
        Runnable r2 = () ->{ n--; };

        /**
         * Lanzamiento de 20000 hilos, 10000 que incrementan 'n' y 10000 que la decrementan, concurrentemente.
         * @see Thread
         */
        for (int i = 0; i<10000; i++){
            new Thread(r1).start();
            new Thread(r2).start();
        }

        System.out.println("Valor esperado: 0. Valor final: " + n); // Impresión por pantalla del valor final de la variable compartida
    }

}
