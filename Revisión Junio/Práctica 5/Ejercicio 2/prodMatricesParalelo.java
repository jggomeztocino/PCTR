import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class prodMatricesParalelo implements Runnable{
    /**
     * Orden de la matriz / vector
     */
    public static int n = 10;
    /**
     * Matriz A
     */
    public static long [][] A = new long [n][n];
    /**
     * Matriz B
     */
    public static long [][] B = new long [n][n];
    /**
     * Vector solución Y [A * B = Y ]
     */
    public static long [][] Y = new long [n][n];
    /**
     * Función para rellenar las matrices A y B.
     */
    public static void rellenarMatrices(){
        Random seed = new Random();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                A[i][j] = seed.nextInt(10);
                B[i][j] = seed.nextInt(10);
            }
        }
    }

    /**
     * ID de la hebra
     */
    private int id;

    // Ecuación de Subramanian
    /**
     * Obtención del número de núcleos:
     */
    private static int Nnld = Runtime.getRuntime().availableProcessors();
    /**
     * Especificación del Coeficiente de bloqueo.
     */
    private static int Cb = 0;

    /**
     * Ecuación de Subramanian: Número de hilos (Nt) = Nº núcleos lógicos (Nnld) / 1 - Coeficiente de bloqueo (Cb) (0: Operaciones aritméticas, 1: demás operaciones)
     */
    private static int Nt = Nnld / (1-Cb);

    /**
     * Espacio de trabajo a procesar:
     */
    private int size = n/Nt;
    private int resto = 0;

    /**
     * Constructor de la clase
     * @param id ID de la hebra
     */
    public prodMatricesParalelo(int id){
        this.id = id;
        if(id==n){
            this.resto = n%Nt;
        }
    }

    /**
     * Implementación de la función de la Interfaz Runnable,
     * donde figura el código concurrente a ejecutar por un Thread.
     * @see Runnable
     */
    public void run() {
        for (int i = (id-1) * size; i < id * size + this.resto; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    Y[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    /**
     * Hilo principal
     * @param args Argumentos de la línea de consola
     */
    public static void main(String[] args) {
        rellenarMatrices();
        ExecutorService executor = Executors.newFixedThreadPool(Nt);
        for(int i=0;i<Nt;i++){
            executor.execute(new prodMatricesParalelo(i+1));
        }
        executor.shutdown();
        while(!executor.isTerminated());
        for(int i = 0; i < n; i++){
            System.out.println(Arrays.toString(Y[i]));
        }
    }
}
