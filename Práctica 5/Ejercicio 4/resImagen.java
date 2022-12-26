import java.util.Date;
import java.util.Random;

/**
 * @autor Jesús Gómez
 * @version 1.0
 */
public class resImagen {
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
    resImagen(){
        Random seed = new Random();
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                imagen[i][j] = seed.nextInt(255);
            }
        }
    }

    /**
     * Operador de resaltado de grises,
     * implementado de manera secuencial.
     */
    public static void operadorResaltado(){

        for(int i = 0; i < k; i++){

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
     */
    public static void main(String[] args) {
        new resImagen();

        /**
         * Preparación y activación del cronómetro.
         */
        Date d = new Date();
        long inicCronom = System.currentTimeMillis(); // Se prepara el cronometro
        d.setTime(inicCronom); // Se activa el cronómetro

        operadorResaltado();

        /**
         * Parada del cronómetro y muestra del tiempo.
         */
        long finCronom = System.currentTimeMillis(); // Se para el cronómetro
        d.setTime(finCronom);
        System.out.println("Nº hebras: 1. Tiempo: "+ (finCronom - inicCronom) + " ms.");
    }
}
