import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 *                   prodEscalarParalelo
 * Clase que implementa el producto escalar de dos vectores,
 * de manera paralela.
 * @see Thread
 * @see prodEscalar
 *
 * @author Jesús Gómez
 * @version 1.0
 */

public class prodEscalarParalelo extends Thread{

    // Si v y w son los vectores, el producto escalar se calcula como la sumatoria de V(i)*W(i)

    int id; // Id (único) de cada hebra

    int i; // Inicio del fragmento del vector

    int f; // Fin del fragmento del vector

    public static long [] vector1 = new long[1000000]; // Vector 1 de 10^6 elementos

    public static long [] vector2 = new long[1000000]; // Vector 2 de 10^6 elementos

    public static int nHebras = 10; // Número de hebras

    public static long [] productoParcial = new long[nHebras];

    /**
     * Constructor de clase
     * @param idHebra ID de la hebra.
     * @param inicio Inicio del fragmento del vector.
     * @param fin Final del fragmento del vector.
     */
    public prodEscalarParalelo(int idHebra, int inicio, int fin){
        id = idHebra;
        i = inicio;
        f = fin;
    }

    /**
     * Método heredado de Thread
     * @see Thread
     * Implementa el comportamiento de las hebras.
     * Opera el producto escalar de los fragmentos de vector correspondientes,
     * y almacena el resultado en su ranura de resultado.
     */
    public void run(){
        for(int acumulador = i; acumulador<f; acumulador++){
            productoParcial[id] += vector1[acumulador] * vector2[acumulador];
        }
    }

    /**
     * Hilo principal donde se realizan las acciones oportunas.
     * @param args No se usa.
     */
    public static void main (String [] args){

        // Inicialización de los vectores con los valores 1, 2, 3, ..., 1000000.
        Arrays.setAll(vector1,(index) -> 1 + index);
        Arrays.setAll(vector2,(index) -> 1 + index);

        // Se crean 10 hilos (vacíos)
        Thread t0;
        Thread t1;
        Thread t2;
        Thread t3;
        Thread t4;
        Thread t5;
        Thread t6;
        Thread t7;
        Thread t8;
        Thread t9;

        long resultado = 0; // Variable que almacenará el resultado

        Date d = new Date();

        // Preparación de la medición de tiempo
        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");

        long inicCronom = 0;

        long finCronom = 0;

        /**
         * Bloque de captura de excepciones
         * El método .join(), heredado de Thread,
         * @see Thread
         * puede lanzar la excepción "InterruptedException",
         * @see InterruptedException
         */
        try{
            switch (nHebras) {
                case 2 -> {
                    // Usando 2 hilos
                    t0 = new prodEscalarParalelo(0, 0, 500000);
                    t1 = new prodEscalarParalelo(1, 500000, 1000000);

                    // Se activa el cronómetro
                    inicCronom = System.currentTimeMillis();
                    d.setTime(inicCronom);

                    // Se lanzan los hilos
                    t0.start();
                    t1.start();

                    // Se para la ejecución del hilo principal
                    t0.join();
                    t1.join();

                    // Se suman las ranuras de resultado
                    resultado = productoParcial[0] + productoParcial[1];

                    // Se para el cronómetro
                    finCronom = System.currentTimeMillis();
                    d.setTime(finCronom);
                }
                case 4 -> {
                    // Usando 4 hilos
                    t0 = new prodEscalarParalelo(0, 0, 250000);
                    t1 = new prodEscalarParalelo(1, 250000, 500000);
                    t2 = new prodEscalarParalelo(2, 500000, 750000);
                    t3 = new prodEscalarParalelo(3, 750000, 1000000);

                    // Se activa el cronómetro
                    inicCronom = System.currentTimeMillis();
                    d.setTime(inicCronom);

                    // Se lanzan los hilos
                    t0.start();
                    t1.start();
                    t2.start();
                    t3.start();

                    // Se para la ejecución del hilo principal
                    t0.join();
                    t1.join();
                    t2.join();
                    t3.join();

                    // Se suman las ranuras de resultado
                    resultado = productoParcial[0] + productoParcial[1] +
                            productoParcial[2] + productoParcial[3];

                    // Se para el cronómetro
                    finCronom = System.currentTimeMillis();
                    d.setTime(finCronom);
                }
                case 8 -> {
                    // Usando 8 hilos
                    t0 = new prodEscalarParalelo(0, 0, 125000);
                    t1 = new prodEscalarParalelo(1, 125000, 250000);
                    t2 = new prodEscalarParalelo(2, 250000, 375000);
                    t3 = new prodEscalarParalelo(3, 375000, 500000);
                    t4 = new prodEscalarParalelo(4, 500000, 625000);
                    t5 = new prodEscalarParalelo(5, 625000, 750000);
                    t6 = new prodEscalarParalelo(6, 750000, 875000);
                    t7 = new prodEscalarParalelo(7, 875000, 1000000);

                    // Se activa el cronómetro
                    inicCronom = System.currentTimeMillis();
                    d.setTime(inicCronom);

                    // Se lanzan los hilos
                    t0.start();
                    t1.start();
                    t2.start();
                    t3.start();
                    t4.start();
                    t5.start();
                    t6.start();
                    t7.start();

                    // Se para la ejecución del hilo principal
                    t0.join();
                    t1.join();
                    t2.join();
                    t3.join();
                    t4.join();
                    t5.join();
                    t6.join();
                    t7.join();

                    // Se suman las ranuras de resultado
                    resultado = productoParcial[0] + productoParcial[1] +
                            productoParcial[2] + productoParcial[3] +
                            productoParcial[4] + productoParcial[5] +
                            productoParcial[6] + productoParcial[7];

                    // Se para el cronómetro
                    finCronom = System.currentTimeMillis();
                    d.setTime(finCronom);
                }
                case 10 -> {
                    // Usando 10 hilos
                    t0 = new prodEscalarParalelo(0, 0, 100000);
                    t1 = new prodEscalarParalelo(1, 100000, 200000);
                    t2 = new prodEscalarParalelo(2, 200000, 300000);
                    t3 = new prodEscalarParalelo(3, 300000, 400000);
                    t4 = new prodEscalarParalelo(4, 400000, 500000);
                    t5 = new prodEscalarParalelo(5, 500000, 600000);
                    t6 = new prodEscalarParalelo(6, 600000, 700000);
                    t7 = new prodEscalarParalelo(7, 700000, 800000);
                    t8 = new prodEscalarParalelo(8, 800000, 900000);
                    t9 = new prodEscalarParalelo(9, 900000, 1000000);

                    // Se activa el cronómetro
                    inicCronom = System.currentTimeMillis();
                    d.setTime(inicCronom);

                    // Se lanzan los hilos
                    t0.start();
                    t1.start();
                    t2.start();
                    t3.start();
                    t4.start();
                    t5.start();
                    t6.start();
                    t7.start();
                    t8.start();
                    t9.start();

                    // Se para la ejecución del hilo principal
                    t0.join();
                    t1.join();
                    t2.join();
                    t3.join();
                    t4.join();
                    t5.join();
                    t6.join();
                    t7.join();
                    t8.join();
                    t9.join();

                    // Se suman las ranuras de resultado
                    resultado = productoParcial[0] + productoParcial[1] +
                            productoParcial[2] + productoParcial[3] +
                            productoParcial[4] + productoParcial[5] +
                            productoParcial[6] + productoParcial[7] +
                            productoParcial[8] + productoParcial[9];

                    // Se para el cronómetro
                    finCronom = System.currentTimeMillis();
                    d.setTime(finCronom);
                }

                default -> {

                }

            }

        }catch(InterruptedException e){

            System.out.println("Excepción capturada");

        }

        System.out.println("Resultado: " + resultado);
        System.out.println("Nº de hebras: " + nHebras + ". Tiempo empleado: " + (finCronom-inicCronom) + " ms.");

    }

}
