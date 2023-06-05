import mpi.*;

import java.util.Arrays;

/**
 *                          prodInterno
 * Clase para calcular el producto interno entre dos vectores usando MPI.
 * @author Jesús Gómez
 * @version 1.0
 */

// COMPILADO Y EJECUCIÓN DEL CÓDIGO
    // COMPILADO --> javac -cp .:$MPJ_HOME/lib/mpj.jar prodInterno.java
    // EJECUCIÓN --> mpjrun.sh -np 2 prodInterno (.sh en Linux, use .bat si ejecuta el programa en Windows)

public class prodInterno {
    /**
     * Hilo principal.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Inicializar MPI
        MPI.Init(args);
        // Obtener el ID del proceso
        int id = MPI.COMM_WORLD.Rank();
        // Obtener el número total de procesos
        int nHilos = MPI.COMM_WORLD.Size();
        // Establecemos el tamaño de los vectores
        int size = 4;

        /**
         * Vectores a utilizar para calcular el producto interno.2
         * El tamaño será proporcional al número de procesos
         */
        int[] vector1 = new int[size];
        int[] vector2 = new int[size];

        /**
         * Resultado del producto interno.
         */
        int resultado[] = new int [1];
        resultado[0] = 0;

        // Si el proceso es el master/root (id = 0)
        if (id == 0) {

            // Asignar valores a los vectores (los rellena secuencialmente)
            Arrays.setAll(vector1, i->i+1);
            Arrays.setAll(vector2, i->i+1);

            /*
              Sintaxis Send:
              --> Send(buf, offset, count, datatype, destination, tag)
              Donde:
              "buf" es el buffer de datos que se va a enviar o recibir.
              "offset" es la posición de inicio del buffer de datos.
              "count" es el número de elementos que se van a enviar o recibir.
              "datatype" es el tipo de datos que se van a enviar o recibir.
              "destination" es el proceso destino para el método Send y la fuente para el método Recv.
              "tag" es un valor entero que se utiliza para identificar el mensaje.
             */
            // Enviar los vectores al proceso slave (id = 1)
            MPI.COMM_WORLD.Send(vector1, 0, vector1.length, MPI.INT, 1, 99);
            MPI.COMM_WORLD.Send(vector2, 0, vector2.length, MPI.INT, 1, 100);

            /*
              Sintaxis Recv:
              --> Recv(buf, offset, count, datatype, source, tag)
              Donde:
              "buf" es el buffer de datos que se va a enviar o recibir.
              "offset" es la posición de inicio del buffer de datos.
              "count" es el número de elementos que se van a enviar o recibir.
              "datatype" es el tipo de datos que se van a enviar o recibir.
              "source" es el proceso origen para el método Send y la fuente para el método Recv.
              "tag" es un valor entero que se utiliza para identificar el mensaje.
             */
            // Recibir el resultado del proceso slave
            MPI.COMM_WORLD.Recv(resultado, 0, 1, MPI.INT, 1, 101);
            // Imprimir el resultado
            System.out.println("Resultado: " + resultado[0]);
        }
        // Si el proceso es el slave (id = 1)
        else if (id == 1) {
            // Recibir los vectores del proceso master
            MPI.COMM_WORLD.Recv(vector1, 0, vector1.length, MPI.INT, 0, 99);
            MPI.COMM_WORLD.Recv(vector2, 0, vector2.length, MPI.INT, 0, 100);
            // Calcular el producto interno
            for (int i = 0; i < size; i++) {
                resultado[0] += vector1[i] * vector2[i];
            }
            // Enviar el resultado al proceso master
            MPI.COMM_WORLD.Send(resultado, 0, 1, MPI.INT, 0, 101);
        }

        // Finalizar MPI
        MPI.Finalize();
    }
}
