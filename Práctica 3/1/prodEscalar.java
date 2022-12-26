import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 *                      prodEscalar
 * Clase que implementa el producto escalar de dos vectores,
 * de manera secuencial.
 * @see prodEscalarParalelo
 *
 * @author Jesús Gómez
 * @version 1.0
 */

public class prodEscalar {

    /**
     * Función auxilir que imprime una serie de valores dado un vector.
     * Útil para hacer rápidas comprobaciones de corrección.
     * @param v Vector a imprimir
     */
    static void imprimirValores(long [] v){
        for(int i = 0; i<1000000; i++){

            if(i%100000 == 0){
                System.out.print(v[i] + " ");
            }

        }
    }

    /**
     * Hilo principal donde se realizan las acciones oportunas.
     * @param args No se usa.
     */
    public static void main(String[] args) {

        // Se crean dos arrays de 10^6 elementos cada uno,
        // se inicializan con los valores 1, 2, 3, ..., 1000000.
        long [] vector1 = new long[1000000];
        Arrays.setAll(vector1,(index) -> 1 + index);
        long [] vector2 = new long[1000000];
        Arrays.setAll(vector2,(index) -> 1 + index);

        // Variable donde se almacenará el resultado del producto escalar.
        long resultadoProdEscalar = 0;

        Date d = new Date();

        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS"); // Formato a establecer los tiempos.

        long inicCronom = System.currentTimeMillis(); // Se prepara el cronómetro

        d.setTime(inicCronom); // Se activa el cronómetro

        // Producto escalar de dos vectores
        for(int i = 0; i<1000000; i++){

            resultadoProdEscalar += vector1[i] * vector2[i];

        }

        long finCronom = System.currentTimeMillis(); // Se para el cronómetro

        d.setTime(finCronom); // Se formatea el tiempo tomado

        /**
         * Descomentar en caso de querer imprimir los valores y comprobar la correción del programa.
        System.out.print("Vector 1: ");
        imprimirValores(vector1);
        System.out.print("\nVector 2: ");
        imprimirValores(vector2);
        System.out.print("\nResultado: " + resultadoProdEscalar);
        */

        System.out.println("\nTiempo empleado: " + (finCronom-inicCronom) + " ms.");

    }
}

