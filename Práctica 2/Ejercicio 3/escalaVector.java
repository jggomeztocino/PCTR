import java.util.Arrays;
import java.util.Scanner;

/**
 *                                 escalaVector
 * Clase escalaVector, que contiene el hilo principal. Este realizará el escalado
 * de un vector de manera secuencial.
 *  * @author Jesús Gómez
 *  * @version 1.0
 */
public class escalaVector {

    /**
     * Función auxiliar para limpiar la consola
     */
    static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Hilo principal, que contiene la demostración de uso del programa secuencial.
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


        /**
         * Inicializamos el vector (en orden creciente) y mostramos algunos valores por pantalla.
         */
        System.out.println("Algunos valores del Vector inicial:");
        for(int i=0; i < n; i++){
            v[i] = i;
            if(i%500000==0) { // Optamos por imprimir un número reducido de valores para la rápida comprobación del funcionamiento.
                System.out.print(v[i] + " ");
            }
        }

        System.out.println(); // Salto de línea

        /**
         * Bloque de código que pregunta por el escalar a aplicar.
         * En caso de ser inválido, no se tendrá en cuenta y se volverá a pedir.
         */
        while(escalar<=1) {
            System.out.print("Introduzca el escalar a multiplicar: ");
            input = new Scanner(System.in); // Leemos por pantalla
            escalar = input.nextInt(); // Extraemos y asignamos a "escalar" el entero introducido.
        }

        /**
         * Escalamos el vector y mostramos los mismos valores que mostramos antes, pero esta vez escalados.
         * */
        System.out.println("Algunos valores del Vector escalado:");
        for(int i=0; i < n; i++){
            v[i] = v[i] * escalar;
            if(i%500000==0) { // Optamos por imprimir un número reducido de valores para la rápida comprobación del funcionamiento.
                System.out.print(v[i] + " ");
            }
        }

    }
}
