import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que implementa la funcionalidad lectores-escritores,
 * mediante el uso de un monitor.
 *
 * @see recurso
 * @see usalectorEscritor
 *
 * @author Jesús Gómez
 * @version 2.0
 */
public class lectorEscritor {
    /**
     * Variables del monitor, privadas y sin inicializar.
     */
    private int lectores; // Número de lectores leyendo concurrentemente
    private Condition lector; // Condición
    private Condition escritor; // Condición
    private boolean escribiendo; // Indica si hay un escritor activo
    private ReentrantLock cerrojo;

    /**
     * Constructor del monitor,
     * que contiene el código de inicialización.
     */
    public lectorEscritor() {
        lectores = 0;
        escribiendo = false;
        cerrojo = new ReentrantLock();
        lector = cerrojo.newCondition();
        escritor = cerrojo.newCondition();
    }

    /**
     * Método synchronized que inicia la acción de un lector.
     */
    public void iniciaLectura() throws InterruptedException {
        cerrojo.lock();
        try{
            while(escribiendo){
                lector.await();
            }
            lectores++;
            lector.signalAll();
        }finally {
            cerrojo.unlock();
        }
    }

    /**
     * Método synchronized que finaliza la acción de un lector.
     */
    public void acabarLectura(){
        cerrojo.lock();
        try{
            lectores--;
            if(lectores == 0){
                escritor.signalAll();
            }
        }finally{
            cerrojo.unlock();
        }
    }

    /**
     * Método synchronized que inicia la acción de un escritor.
     */
    public void iniciarEscritura() throws InterruptedException {
        cerrojo.lock();
        try{
            while(lectores!=0 || escribiendo){
                escritor.await();
            }
            escribiendo = true;
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Método synchronized que finaliza la acción de un escritor.
     */
    public void acabarEscritura(){
        cerrojo.lock();
        try{
            escribiendo = false;
            escritor.signalAll();
            lector.signalAll();
        }finally {
            cerrojo.unlock();
        }
    }

    /**
     * Varios lectores pueden leer concurrentemente, pero solo un escritor puede escribir a la vez.
     * Si hay un escritor escribiendo, los lectores no pueden leer.
     */
}
