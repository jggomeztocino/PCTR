import mpi.*;

import java.util.Arrays;

/**                             escalMultiple
 * Clase que implementa un programa donde el proceso master/root (id=0) envía
 * un array de diez componentes enteros a cuatro procesos slaves (id=1, 2, 3, 4)
 * utilizando Bcast. Cada slave efectúa un escalado del vector según su identificador
 * y lo imprime en pantalla.
 *
 * @author Jesús Gómez
 * @version 1.0
 */

// COMPILADO Y EJECUCIÓN DEL CÓDIGO
    // COMPILADO --> javac -cp .:$MPJ_HOME/lib/mpj.jar escalMultiple.java
    // EJECUCIÓN --> mpjrun.sh -np 2 prodInterno (.sh en Linux, use .bat si ejecuta el programa en Windows)

public class escalMultiple {
    /**
     * Método principal que se encarga de inicializar el array, enviarlo a los procesos
     * slaves utilizando Bcast, multiplicar cada componente del array por el identificador
     * del proceso y finalmente imprimir el array en pantalla.
     *
     * @param args Argumentos del programa. No se utilizan en este caso.
     * @throws Exception Si ocurre algún error durante la ejecución del programa.
     */

    public static void main(String[] args) throws Exception {
        // Inicializamos la biblioteca MPJ
        MPI.Init(args);

        // Obtenemos el identificador del proceso actual
        int id = MPI.COMM_WORLD.Rank();

        // Obtenemos el número total de procesos
        int size = MPI.COMM_WORLD.Size();

        // Creamos el array de 10 componentes
        int[] array = new int[10];

        // Inicializamos el array en el proceso root
        if (id == 0) {
            for (int i = 0; i < array.length; i++) {
                array[i] = i + 1;
            }
        }

        /**
         * Sintaxis Bcast
         */
        // Enviamos el array a todos los procesos slaves utilizando Bcast
        MPI.COMM_WORLD.Bcast(array, 0, array.length, MPI.INT, 0);

        // Los procesos slaves multiplican cada componente del array por su identificador
        if (id!=0){
            for (int i = 0; i < array.length; i++) {
                array[i] *= id;
            }
        }

        // Imprimimos el array en cada proceso
        if (id == 0) {
            System.out.println("Proceso root [" + id + "]: " + Arrays.toString(array));
        } else {
            System.out.println("Proceso slave [" + id + "]: " + Arrays.toString(array));
        }

        // Finalizamos la ejecución de la biblioteca MPJ
        MPI.Finalize();
    }
}
