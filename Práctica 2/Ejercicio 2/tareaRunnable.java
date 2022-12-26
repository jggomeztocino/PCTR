/**
 *                                     clase tareaRunnable
 * Clase que implementa la interfaz Runnable e implementa el método run para su uso concurrente
 * @author Jesús Gómez
 * @version 1.0
 * @see Runnable
 * @see Usa_tareaRunnable
 */
public class tareaRunnable implements Runnable{

    static int n = 0; // Atributo compartido por todas las instancias

    int iteraciones = 0; // Número de iteraciones (único para cada instancia de la clase)

    /**
     * Constructor de la clase tareaRunnable
     * Inicializa el número de iteraciones e, implícitamente, el tipo de hilo (+/-)
     * @param i (Número de iteraciones)
     */
    tareaRunnable(int i){
        iteraciones = i;
    }

    /**
     * Método observador
     * @return n. (Devuelve la variable estática n)
     */
    static int getN(){
        return n;
    }

    /**
     * Método implementado de la interfaz Runnable, heredado a su vez de Thread.
     * @see Runnable
     * @see Thread
     * Implementa el comportamiento de los hilos una vez se lancen.
     * Dependiendo del signo (+/-) del valor de iteraciones especificado al crear el objeto,
     * incrementará o decrementará en "iteraciones" veces la variable compartida 'n'.
     */
    public void run(){
        if(iteraciones>=0){ // Si el número de vueltas es positivo...
            for (int i=0;i<iteraciones;i++){ // Incrementará 'n' en el número de iteraciones indicado
                ++n;

                //System.out.println("Iteracion: " + i + ". n = " + n);
                // No es idóneo actualizar a la vez que imprimimos, ya que la pantalla es un elemento único y tiende a sincronizar los elementos involucrados.

            }
        }else{ // Si el número de vueltas es negativo...
            for (int i=0;i>iteraciones;i--){ // Decrementará 'n' en el número de iteraciones indicado
                --n;

                //System.out.println("Iteracion: " + (-i) + ". n = " + n);
                // No es idóneo actualizar a la vez que imprimimos, ya que la pantalla es un elemento único y tiende a sincronizar los elementos involucrados.

            }
        }
    }
}
