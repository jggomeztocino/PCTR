import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @autor Jesús Gómez
 * @version 1.0
 */
public class resImagenPar implements Runnable{

    int numeroHilo = 0;
    int nFilas = 0;

    /**
     * Ecuación de Subramanian.
     */
    static int nNuc = Runtime.getRuntime().availableProcessors();
    static float Cb = 0f;
    static int poolSize = (int)(nNuc/(1-Cb));

    /**
     * Orden de la matriz de imagen
     */
    static int k = 10000;
    /**
     * Matriz que almacena la imagen
     * de orden k
     */
    static int[][] imagen = new int[k][k];

    /**
     * Constructor de clase.
     * Inicia la imagen con niveles aleatorios
     * de gris.
     * @see Random
     */
    resImagenPar(int n){
        numeroHilo = n;
    }

    /**
     * Inicia la imagen con niveles aleatorias de grises.
     * @see Random
     */
    public static void iniciarImagen(){
        Random seed = new Random();
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                imagen[i][j] = seed.nextInt(255);
            }
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
        int ini = 0;
        int fin = 0;
        nFilas = k/poolSize;
        ini = numeroHilo*nFilas;
        fin = ini + nFilas;
        if (numeroHilo+1==poolSize){ // Si es la última hebra, que asigne las filas sobrantes también.
            nFilas += k - fin;
        }
        operadorResaltado(ini,fin);
    }

    /**
     * Operador de resaltado de grises,
     * implementado de manera paralela dentro de un rango.
     * @param inicio Inicio del fragmento de matriz asignada.
     * @param fin Fin del fragmento de matriz asignada.
     */
    public static void operadorResaltado(int inicio, int fin){

        for(int i = inicio; i < fin; i++){

            for (int j = 0; j < k; j++) {

                imagen[i][j] *= 4;

                if(i+1 < k){
                    imagen[i][j] -= imagen[i+1][j];
                }

                if(j+1 < k){
                    imagen[i][j] -= imagen[i][j+1];
                }

                if(i-1 >= 0){
                    imagen[i][j] -= imagen[i-1][j];
                }

                if(j-1 >= 0){
                    imagen[i][j] -= imagen[i][j-1];
                }

                imagen[i][j] /= 8;

                if(imagen[i][j]>255){
                    imagen[i][j] = 255;
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
    public static void main(String[] args) {
        iniciarImagen();

        /**
         * Preparación y activación del cronómetro.
         */
        Date d = new Date();
        long inicCronom = System.currentTimeMillis(); // Se prepara el cronometro
        d.setTime(inicCronom); // Se activa el cronómetro

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        for(int i=0;i<poolSize;i++){
            executor.execute(new resImagenPar(i));
        }
        executor.shutdown();
        while(!executor.isTerminated());

        /**
         * Parada del cronómetro y muestra del tiempo.
         */
        long finCronom = System.currentTimeMillis(); // Se para el cronómetro
        d.setTime(finCronom);
        System.out.println("Nº hebras: " + nNuc + ". Tiempo: "+ (finCronom - inicCronom) + " ms.");
    }
}