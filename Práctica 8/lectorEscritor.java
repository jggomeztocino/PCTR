/**
 * Clase que implementa la funcionalidad lectores-escritores,
 * mediante el uso de un monitor.
 *
 * @see recurso
 * @see usalectorEscritor
 *
 * @author Jesús Gómez
 * @version 1.0
 */
public class lectorEscritor {
    /**
     * Variables del monitor, privadas y sin inicializar.
     */
    private static int lectores; // Número de lectores leyendo concurrentemente
    //private static boolean lector; // Condición
    //private static boolean escritor; // Condición
    private static boolean escribiendo; // Indica si hay un escritor activo

    /**
     * Constructor del monitor,
     * que contiene el código de inicialización.
     */
    public lectorEscritor() {
        lectores = 0;
        /*lector = false;
        escritor = false;*/
        escribiendo = false;
    }

    /**
     * Método synchronized que inicia la acción de un lector.
     */
    public synchronized void iniciaLectura(){
        while(escribiendo){
            //while(!lector){
                try{
                    wait();
                } catch (InterruptedException ignore){}
            //}
        }
        lectores++;
        notifyAll();
    }

    /**
     * Método synchronized que finaliza la acción de un lector.
     */
    public synchronized void acabarLectura(){
        lectores--;
        if(lectores == 0){
            //escritor = true;
            notifyAll();
        }
    }

    /**
     * Método synchronized que inicia la acción de un escritor.
     */
    public synchronized void iniciarEscritura(){
        while(lectores!=0 || escribiendo){
            try{
                wait();
            } catch(InterruptedException ignore){}
        }
        escribiendo = true;
    }

    /**
     * Método synchronized que finaliza la acción de un escritor.
     */
    public synchronized void acabarEscritura(){
        escribiendo = false;
        notifyAll();
    }

    /**
     * Varios lectores pueden leer concurrentemente, pero solo un escritor puede escribir a la vez.
     * Si hay un escritor escribiendo, los lectores no pueden leer.
     */
}
