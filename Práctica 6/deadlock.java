import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase que implementará una situación de interbloqueo.
 * @author Jesús Gómez
 * @version 1.0
 */
public class deadlock implements Runnable{

    /**
     * Cerrojos sobre los que trabaja una instancia determinada
     */
    private final Object cerrojo1;
    private final Object cerrojo2;

    /**
     * Constructor de clase.
     * Asigna los cerrojos sobre los que va a trabajar una instancia
     * @param cerrojo1 Objeto cerrojo 1 (pasado por referencia)
     * @param cerrojo2 Objeto cerrojo 2 (pasado por referencia)
     */
    public deadlock(Object cerrojo1, Object cerrojo2){
        this.cerrojo1 = cerrojo1;
        this.cerrojo2 = cerrojo2;
    }

    /**
     * Método heredado de Thread,
     * e implementado por Runnable.
     * Define el comportamiento de los hilos.
     * @see Thread
     * @see Runnable
     */
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " adquiere el cerrojo en " + cerrojo1);
        synchronized (cerrojo1) {
            System.out.println(name + " bloquea el cerrojo " + cerrojo1);
            cambioContexto();
            System.out.println(name + " adquiere el cerrojo en " + cerrojo2);
            synchronized (cerrojo2) {
                System.out.println(name + " bloquea el cerrojo " + cerrojo2);
                cambioContexto();
            }
            System.out.println(name + " libera el cerrojo " + cerrojo2);
        }
        System.out.println(name + " libera el cerrojo " + cerrojo1);
        System.out.println(name + " termina su ejecución.");
    }

    /**
     * Función que fuerza el cambio de contexto en los hilos.
     */
    private void cambioContexto(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Excepción capturada
        }
    }

    /**
     * Hilo principal donde se demuestra el funcionamiento del programa.
     * @param args No se usa
     * @throws InterruptedException Lanzada por los métodos .join()
     * @see ExecutorService
     * @see Executors
     * @see Object
     */
    public static void main(String[] args) throws InterruptedException {

        Object cerrojo1 = new Object();
        Object cerrojo2 = new Object();
        Object cerrojo3 = new Object();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new deadlock(cerrojo1, cerrojo2));
        executor.execute(new deadlock(cerrojo2, cerrojo3));
        executor.execute(new deadlock(cerrojo3, cerrojo1));

        executor.shutdown();
        while(!executor.isShutdown());

    }
}
/**
 * Se produce interbloqueo, ya que los cerrojos
 * son recursos compartidos (al ser pasados por referencia)
 * entre las hebras a las que sean asignados.
 * Los hilos conseguirán adquirir los primeros cerrojos,
 * pero cuando intentan adquirir en la segunda "vuelta"
 * los cerrojos ya se encuentran bloqueados por otro hilo,
 * lo que genera una dependencia que no permitirá terminar
 * el programa.
 */
