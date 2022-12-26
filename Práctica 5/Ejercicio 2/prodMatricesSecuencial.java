import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * prodMatricesSecuencial
 * Clase que realizará la multiplicación de dos matrices,
 * de manera secuencial.
 *
 * No es necesario hacer ninguna modificación en la 5ª asignación,
 * ya que solo disponemos de un único hilo.
 *
 * @author Jesús Gómez
 * @version 1.0
 */
public class prodMatricesSecuencial {
    /**
     * Orden de la matriz / vector
     */
    public static int n = 10000;
    /**
     * Matriz A
     */
    public static long [][] A = new long [n][n];
    /**
     * Matriz b
     */
    public static long [][] B = new long [n][n];
    /**
     * Vector solución y [A * b = y ]
     */
    public static long [][] Y = new long [n][n];

    /**
     * Hilo principal donde se realizan las acciones oportunas.
     * @param args No se usa.
     */
    public static void main(String [] args){

        Random seed = new Random(); // Generador de números aleatorios

        // Inicialización de los vectores y la matriz con valores aleatorios.
        for (int i=0; i< n; i++){
            for (int j=0; j<n; j++){
                A[i][j] = seed.nextInt(10);
                B[i][j] = seed.nextInt(10);
                Y[i][j] = 0;
            }
        }

        Date d = new Date();

        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS"); // Formato a establecer los tiempos.

        long inicCronom = System.currentTimeMillis(); // Se prepara el cronómetro

        d.setTime(inicCronom); // Se activa el cronómetro


        // Multiplicación de la matriz A y el vector b
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n ; k++){
                    Y[i][j] += A[i][j] * B[j][i];
                }
            }
        }

        long finCronom = System.currentTimeMillis(); // Se para el cronómetro

        d.setTime(finCronom); // Se formatea el tiempo tomado

        /*// Se imprimen las matrices
        for (int i = 0; i < n; i++){
            System.out.print("\n| ");
            for(int j = 0; j < n; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.print(" |  | " + b[i] + " |  | " + y[i] + " |");
        }*/

        System.out.println("\nTiempo empleado: " + (finCronom-inicCronom) + " ms.");

    }
}

