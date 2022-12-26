import java.util.Scanner;

/**
 *                                                       escalaVPar
 * Clase escalaVPAr, contiene una subclase "myThread" que implementa la interfaz Runnable para el posterior encapsulado en hilos.
 * Además, se incluye el hilo principal donde se demostrará el funcionamiento concurrente del escalado de un vector.
 * @author Jesús Gómez
 * @version 1.0
 * @see Runnable
 * @see Thread
 * @see escalaVector
 */
public class escalaVPar{

    /**
     * Clase myThread que implementa la interfaz Runnable y define el comportamiento de los hilos.
     * @see Runnable
     */
    public static class myThread implements Runnable{
        int [] vector; // Variable que almacenará la dirección de memoria del vector a trabajar
        int inicio = 0; // Variable que almacenará el índice que marca el inicio de fragmento de vector sobre el que se va a trabajar.
        int fin = 0; // Variable que almacenará el índice que marca el final de fragmento de vector sobre el que se va a trabajar.
        int escalar = 0; // Variable que almacenará el escalar por el que se multiplicará el fragmento de vector

        /**
         * Constructor de la clase myThread, que recibiendo una serie de argumentos,
         * asigna un valor al inicio, final, escalar y vector.
         * @param i (Inicio)
         * @param f (Final)
         * @param e (Escalar)
         * @param v (Vector)
         */
        myThread(int i, int f, int e, int [] v){
            inicio = i;
            fin = f;
            escalar = e;
            vector = v; // Aquí realmente se asigna una dirección de memoria, para evitar la copia y trabajar sobre el mismo vector.
        }

        /**
         * Método heredado de Thread, implementado por la interfaz Runnable
         * Define el comportamiento del hilo una vez se lance.
         * Asignado un vector, unas posiciones inicio y final, y un escalar a aplicar,
         * se realiza el escalado (escalar > 1) o relleno (escalar == 1) del fragmento del vector.
         * @see Thread
         * @see Runnable
         */
        public void run(){
            for(int i = inicio; i<fin; i++){
                if(escalar==1){
                    vector[i] = i;
                }else{
                    vector[i] = vector[i]*escalar;
                }
                if(i%500000==0) { // Optamos por imprimir un número reducido de valores para la rápida comprobación del funcionamiento.
                    System.out.print(vector[i] + " ");
                }
            }
        }
    }

    /**
     * Función auxiliar para limpiar la consola
     */
    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Hilo principal, que contiene la demostración de uso del programa concurrente
     * @param args (No se usa)
     */
    public static void main (String [] args){
        int escalar = 1; // Creación e inicialización de la variable escalar

        int n = 0; // Creación e inicialización de la variable tamaño del vector

        Scanner input; // Creación del objeto Scanner que nos permitirá leer por pantalla

        /**
         * Bloque de código que pregunta por un tamaño de vector.
         * En caso de ser inválido, no se tendrá en cuenta y se volverá a pedir.
         */
        while(n<1) {
            clearScreen(); // Limpia la pantalla
            System.out.print("Introduzca el factor para el tamaño del vector (factor * 10⁶): ");
            input = new Scanner(System.in); // Leemos por pantalla
            n = input.nextInt(); // Extraemos y asignamos a 'n' el entero introducido.
        }

        n*=1000000; // Establecemos el orden de 10⁶

        int [] v = new int[n]; // Creamos el vector acorde al tamaño especificado.

        System.out.println("Algunos valores del Vector inicial:");

        /**
         * Lanzamos hilos para asignar valores a las posiciones del vector.
         * Rellenamos el vector en orden creciente y de manera concurrente.
         * El factor escalar aquí es indiferente, ya que inicialmente su valor es 1.
         * @see Thread
         * @see myThread
         */
        for(int i=0; i < n; i+=100){ // Dividimos el vector en fragmentos de 100 unidades.
            new Thread(new myThread(i,i+100, escalar, v)).start();
        }

        System.out.println(); // Salto de línea

        /**
         * Bloque de código que pregunta por un escalar a aplicar.
         * En caso de ser inválido, no se tendrá en cuenta y se volverá a pedir.
         */
        while(escalar<=1) {
            System.out.print("Introduzca el escalar a multiplicar: ");
            input = new Scanner(System.in); // Leemos por pantalla.
            escalar = input.nextInt(); // Extraemos y asignamos a escalar el entero introducido.
        }

        System.out.println("Algunos valores del Vector escalado:");

        /**
         * Lanzamos hilos para sobreescribir valores a las posiciones del vector.
         * Multiplicamos el escalar por todas las posiciones del vector de manera concurrente.
         * @see Thread
         * @see myThread
         */
        for(int i=0; i < n; i+=100){
            new Thread(new myThread(i,i+100, escalar, v)).start();
        }
    }
}
