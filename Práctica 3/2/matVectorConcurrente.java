import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * matVector
 * Clase que realizará la multiplicación de dos matrices,
 * de manera paralela.
 * @see matVector
 * @see Runnable
 * @see Thread
 *
 * @author Jesús Gómez
 * @version 1.0
 */
public class matVectorConcurrente implements Runnable{

    public static int nHebras = 10; // Número de hebras

    public static int n = 10; // Orden de la matriz / vector

    public static long [][] A = new long [n][n]; // Matriz A

    public static long [] b = new long[n]; // Vector b

    public static long [] y = new long[n]; // Vector solución y

    int inicioFila = 0; // Primera fila encargada a la hebra

    int finalFila = 0; // Última fila encargada a la hebra

    /**
     * Constructor de clase
     * @param inicio Primera fila encargada a la hebra
     * @param fin Última fila encargada a la hebra
     */
    matVectorConcurrente(int inicio, int fin){
        inicioFila = inicio;
        finalFila = fin;
    }

    /**
     * Método heredado de Thread e implementado por Runnable
     * @see Runnable
     * @see Thread
     * Implementa el comportamiento de las hebras.
     * Multiplica las filas asignadas y almacena el resultado en el vector solución y.
     */
    public void run() {
        for (int i = inicioFila; i < finalFila; i++){
            for (int j = 0; j < n; j++){
                y[i] += A[i][j] * b[j];
            }
        }
    }

    /**
     * Hilo principal donde se realizan las acciones oportunas.
     * @param args No se usa.
     */
    public static void main (String [] args){

        Random seed = new Random();// Generador de números aleatorios

        // Inicialización de los vectores y la matriz con valores aleatorios.
        for (int i=0; i< n; i++){
            y[i] = 0;
            b[i] = seed.nextInt(10);
            for (int j=0; j<n; j++){
                A[i][j] = seed.nextInt(10);
            }
        }

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


        Date d = new Date();

        // Preparación de la medición de tiempo
        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");

        long inicCronom = 0;

        long finCronom = 0;

        int x = n/nHebras; // Número de filas a asignar

        int resto = n%nHebras; // Resto modular,
        // resultante de dividir el orden de la matriz
        // entre el número de hebras

        // a / b = x b + r
        // a = x1 + x2 + ... + xb + r
        // 17 / 4 = x:4 * b + r:1
        // 17 = 4 + 4 + 4 + 4 + 1

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
                    t0 = new Thread(new matVectorConcurrente(0,x));
                    t1 = new Thread(new matVectorConcurrente(x,x+x+resto));

                    // Se activa el cronómetro
                    inicCronom = System.currentTimeMillis();
                    d.setTime(inicCronom);

                    // Se lanzan los hilos
                    t0.start();
                    t1.start();

                    // Se para la ejecución del hilo principal
                    t0.join();
                    t1.join();

                }
                case 4 -> {
                    // Usando 4 hilos
                    t0 = new Thread(new matVectorConcurrente(0,x));
                    t1 = new Thread(new matVectorConcurrente(x,x+x));
                    t2 = new Thread(new matVectorConcurrente(x+x,x+x+x));
                    t3 = new Thread(new matVectorConcurrente(x+x+x,x+x+x+x+resto));

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
                }
                case 8 -> {
                    // Usando 8 hilos
                    t0 = new Thread(new matVectorConcurrente(0,x));
                    t1 = new Thread(new matVectorConcurrente(x,x+x));
                    t2 = new Thread(new matVectorConcurrente(x+x,x+x+x));
                    t3 = new Thread(new matVectorConcurrente(x+x+x,x+x+x+x));
                    t4 = new Thread(new matVectorConcurrente(x+x+x+x,x+x+x+x+x));
                    t5 = new Thread(new matVectorConcurrente(x+x+x+x+x,x+x+x+x+x+x));
                    t6 = new Thread(new matVectorConcurrente(x+x+x+x+x+x,x+x+x+x+x+x+x));
                    t7 = new Thread(new matVectorConcurrente(x+x+x+x+x+x+x,x+x+x+x+x+x+x+x+resto));

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

                }
                case 10 -> {
                    // Usando 10 hilos
                    t0 = new Thread(new matVectorConcurrente(0,x));
                    t1 = new Thread(new matVectorConcurrente(x,x+x));
                    t2 = new Thread(new matVectorConcurrente(x+x,x+x+x));
                    t3 = new Thread(new matVectorConcurrente(x+x+x,x+x+x+x));
                    t4 = new Thread(new matVectorConcurrente(x+x+x+x,x+x+x+x+x));
                    t5 = new Thread(new matVectorConcurrente(x+x+x+x+x,x+x+x+x+x+x));
                    t6 = new Thread(new matVectorConcurrente(x+x+x+x+x+x,x+x+x+x+x+x+x));
                    t7 = new Thread(new matVectorConcurrente(x+x+x+x+x+x+x,x+x+x+x+x+x+x+x));
                    t8 = new Thread(new matVectorConcurrente(x+x+x+x+x+x+x+x,x+x+x+x+x+x+x+x+x));
                    t9 = new Thread(new matVectorConcurrente(x+x+x+x+x+x+x+x+x,x+x+x+x+x+x+x+x+x+resto));

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
                }
                default -> {

                }
            }
        }catch(InterruptedException e){
            System.out.println("Excepción capturada");
        }
        // Se para el cronómetro
        finCronom = System.currentTimeMillis();
        d.setTime(finCronom);

        // Se imprimen las matrices
        for (int i = 0; i < n; i++){
            System.out.print("\n| ");
            for(int j = 0; j < n; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.print(" |  | " + b[i] + " |  | " + y[i] + " |");
        }

        System.out.println("\nNº de hebras: " + nHebras + ". Tiempo empleado: " + (finCronom-inicCronom) + " ms.");
    }
}
