import mpi.*;

/**
 * Clase para buscar números primos de manera distribuida utilizando MPJ-Express.
 * @author Jesús Gómez
 * @version 1.0
 */

// COMPILADO Y EJECUCIÓN DEL CÓDIGO
    // COMPILADO --> javac -cp .:$MPJ_HOME/lib/mpj.jar distributedIntegers.java
    // EJECUCIÓN --> mpjrun.sh -np 2 prodInterno (.sh en Linux, use .bat si ejecuta el programa en Windows)

public class distributedIntegers {
    public static void main(String[] args) throws Exception {
        // Inicializar MPI
        MPI.Init(args);

        // Obtener el número de procesos y el ID del proceso actual
        int nProcesos = MPI.COMM_WORLD.Size();
        int ID = MPI.COMM_WORLD.Rank();

        // Tamaño del rango de análisis
        int [] rango = new int [1];

        rango[0] = 10000000;

        // Enviar el tamaño del rango de análisis a todos los procesos
        MPI.COMM_WORLD.Bcast(rango, 0, 1, MPI.INT, 0);

        // Cálculo del rango de análisis para cada proceso
        int start = ID * (rango[0] / nProcesos);
        int end = (ID + 1) * (rango[0] / nProcesos);

        // Búsqueda de números primos en el rango correspondiente
        int numPrimos = 0;
        for (int i = start; i < end; i++) {
            if (esPrimo(i)) {
                numPrimos++;
            }
        }

        // Crear una variable acumuladora y sumar el resultado obtenido por este proceso
        int [] resultado = new int [1];
        resultado[0] = numPrimos;

        // Volcar los resultados al proceso 0 mediante Reduce
        int [] resultadoTotal = new int [1];
        resultadoTotal[0] = 0;
        MPI.COMM_WORLD.Reduce(resultado, 0, resultadoTotal,0,1, MPI.INT, MPI.SUM, 0);

        // Si el proceso es el 0, mostrar el resultado final
        if (ID == 0) {
            System.out.println("Número de números primos encontrados: " + resultadoTotal[0]);
        }

        // Finalizar MPI
        MPI.Finalize();
    }

    /**
     * Método para determinar si un número es primo o no.
     *
     * @param num Número a evaluar.
     * @return true si el número es primo, false en caso contrario.
     */
    public static boolean esPrimo(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        // La razón por la que se utiliza la raíz cuadrada del número en el algoritmo es porque,
        // si un número tiene un divisor mayor que su raíz cuadrada,
        // entonces tendrá también un divisor menor que su raíz cuadrada.
        return true;
    }
}



