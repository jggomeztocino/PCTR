import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @autor Jesús Gómez
 * @version 1.0
 * @see Thread
 * @see Runnable
 * @see ExecutorService
 * @see Executors
 * @see Date
 * @see DateFormat
 * @see SimpleDateFormat
 * @see Random
 */
public class prodMatricesParalelo implements Runnable{
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
     * Obtención del número de núcleos
     */
    static int nNuc = Runtime.getRuntime().availableProcessors();
    /**
     * El coeficiente de bloqueo no tiene un cálculo fijo,
     * este variará según la densidad del problema
     * o la potencia del sistema.
     * Aumentaremos este coeficiente gradualmente
     * hasta encontrar el mejor tiempo.
     *
     * Coeficiente de bloqueo: número Cb entre 0 y 1 que mide la
     * fracción (en tanto por uno) de tiempo donde un código esté
     * detenido por latencias de E/S, de red, etc.
     * En arquitecturas multicore de memoria común,
     * la latencia de memoria se suele considerar despreciable.
     *
     * Tipos de Cb:
     * Problemas de computación Numérica, con Cb = 0:
     * -→ Producto escalar de vectores
     * -→ Producto de matrices
     * -→ Ecuación de ondas en un medio bidimensional, integració numérica, etc.
     * - Problemas de cualquier otra naturaleza, con 0 < Cb < 1
     * -→ Implementación de servidores multihebrados
     * -→ Software con alta interacción de red...
     * -→ ... e incluso problemas del primer grupo, cuando se resuelve en arquitecturas heterogéneas
     */
    static float Cb = 0f;
    /**
     * Ecuación Subramanian
     * Determinará el número de hilos óptimo
     * en función del número de núcleos lógicos disponibles
     * y del coeficiente de bloqueo del problema.
     */
    static int poolSize = (int)(nNuc/(1-Cb));
    /**
     * Número del hilo
     */
    int nHilo = 0;
    /**
     * Inicio del rango de matriz sobre la que operar
     */
    int inicioFila = 0;
    /**
     * Final del rango de matriz sobre la que operar
     */
    int finalFila = 0;
    /**
     * Número de filas asignadas a operación a la hebra
     */
    int nFilas = 0; // Número de filas a asignar a cada hebra

    /**
     * Constructor de clase.
     * Calculará el número de filas correspondiente a la hebra,
     * y el rango asignado, además de establecer el número del hilo.
     * @param i Número de hilo
     */
    prodMatricesParalelo(int i){
        nHilo = i;
        nFilas = n/poolSize;
        inicioFila = i*nFilas;
        finalFila = inicioFila + nFilas;
        if (i+1==poolSize){ // Si es la última hebra, que asigne las filas sobrantes también.
            nFilas += n - finalFila;
        }
    }

    /**
     * Método heredado de Thread
     * e implementado por Runnable.
     * Define el comportamiento del hilo.
     * @see Thread
     * @see Runnable
     */
    @Override
    public void run() {
        for (int i = inicioFila; i < finalFila; i++){
            for (int j = 0; j < n; j++){
                for (int k = 0; k < n ; k++){
                    Y[i][j] += A[i][j] * B[j][i];
                }
            }
        }
    }

    /**
     * Hilo principal, prueba del programa desarrollado.
     * @param args No se usa
     * @see Executors
     * @see ExecutorService
     * @see Date
     */
    public static void main (String [] args){
        Random seed = new Random();// Generador de números aleatorios

        // Inicialización de los vectores y la matriz con valores aleatorios.
        for (int i=0; i< n; i++){
            for (int j=0; j<n; j++){
                A[i][j] = seed.nextInt(10);
                B[i][j] = seed.nextInt(10);
                Y[i][j] = 0;
            }
        }

        /**
         * Preparación y activación del cronómetro.
         */
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
        long inicCronom = System.currentTimeMillis();
        d.setTime(inicCronom);

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        for(int i=0;i<poolSize;i++){
            executor.execute(new prodMatricesParalelo(i));
        }
        executor.shutdown();
        while(!executor.isTerminated());

        /**
         * Parada del cronómetro y muestra del tiempo.
         */
        long finCronom = System.currentTimeMillis();
        d.setTime(finCronom);
        System.out.println("Número de hilos: " + poolSize + ". Tiempo empleado: " + (finCronom-inicCronom) + " ms.");
    }
}
