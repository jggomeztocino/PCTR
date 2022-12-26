import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Clase que implementa métodos protegidos frente a la E.M.
 * y métodos que producen sobre-escritura.
 * @author Jesús Gómez
 * @version 1.0
 */
public class heterogenea implements Runnable{

    /**
     * Dato protegido frente a la E.M.
     */
    public static int n = 0;

    /**
     * Dato NO protegido frente a la E.M.
     */
    public static int m = 0;

    /**
     * Atributo que especifica el tipo de hilo.
     */
    int tipoHilo;

    /**
     * Constructor de clase.
     * Especifica el tipo de hilo.
     *
     * @param tipoHilo Tipo de Hilo
     */
    heterogenea(int tipoHilo){
        this.tipoHilo = tipoHilo;
    }

    /**
     * Método synchronized que controla la E.M. al acceder a un dato compartido.
     * Incrementa 'n' en una unidad.
     */
    synchronized static void incrementaN(){
        n++;
    }

    /**
     * Incrementa 'm' en una unidad.
     */
    static void incrementaM(){
        m++;
    }

    /**
     * Método heredado de Thread,
     * e implementado por Runnable.
     * Define el comportamiento de los hilos.
     * @see Thread
     * @see Runnable
     */
    @Override
    public void run() {
        if(tipoHilo==0){
            incrementaN();
        }
        if(tipoHilo==1){
            incrementaM();
        }
    }
}
