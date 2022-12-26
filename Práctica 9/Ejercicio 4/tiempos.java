import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que compara el rendimiento de tres métodos de sincronización y preservación de la E.M.
 *
 * @author Jesús Gómez
 * @version 1.0
 * @see ReentrantLock
 * @see AtomicInteger
 *
 */
public class tiempos {

    /**
     * Objeto utilizado como cerrojo para proteger la sección crítica en el método `fSynchronized`.
     */
    static Object cerrojo = new Object();

    /**
     * Objeto `ReentrantLock` utilizado para proteger la sección crítica en el método `fReentrant`.
     */
    static ReentrantLock cerrojoReentrante = new ReentrantLock();

    /**
     * Variable compartida que es incrementada en la sección crítica en los métodos `fSynchronized` y `fReentrant`.
     */
    static int n = 0;

    /**
     * Variable `AtomicInteger` que es utilizada para incrementar de forma atómica en el método `fAtomic`.
     */
    static AtomicInteger nAtomica = new AtomicInteger(0);

    /**
     * Método que realiza un número dado de iteraciones de un loop que incrementa un contador compartido
     * usando la palabra clave `synchronized` para proteger la sección crítica.
     *
     * @param iter Número de iteraciones.
     * @return Tiempo en milisegundos que tardó en ejecutarse el método.
     */
    public static long fSynchronized(long iter) {
        // Reasignamos el valor inicial de la variable compartida
        n = 0;

        // Tiempo inicial
        Instant inicio = Instant.now();

        // Realizar las iteraciones del loop.
        for (long i = 0; i < iter; i++) {
            // Proteger la sección crítica con el cerrojo.
            synchronized (cerrojo) {
                n++; // Sección crítica.
            }
        }

        // Tomar el tiempo de fin.
        Instant fin = Instant.now();

        // Calcular el tiempo transcurrido.
        Duration tiempo = Duration.between(inicio, fin);

        // Devuelve el tiempo en milisegundos
        return (tiempo.toMillis());
    }


    /**
     * Método que realiza un número dado de iteraciones de un loop que incrementa un contador compartido
     * usando un objeto `ReentrantLock` para proteger la sección crítica.
     *
     * @param iter Número de iteraciones.
     * @return Tiempo en milisegundos que tardó en ejecutarse el método.
     */
    public static long fReentrant(long iter) {
        // Reasignamos el valor inicial de la variable compartida
        n = 0;

        // Tiempo inicial
        Instant inicio = Instant.now();

        for (long i = 0; i < iter; i++) {
            // Bloquear el cerrojo reentrante.
            cerrojoReentrante.lock();
            n++; // Sección crítica.
            // Liberar el cerrojo reentrante.
            cerrojoReentrante.unlock();
        }

        // Tiempo final
        Instant fin = Instant.now();

        // Tiempo transcurrido.
        Duration tiempo = Duration.between(inicio, fin);

        // Devuelve el tiempo en milisegundos
        return (tiempo.toMillis());
    }

    /**
     * Método que realiza un número dado de iteraciones de un bucle que incrementa un contador atómico
     * utilizando una variable Atomic para realizar el incremento de forma atómica.
     *
     * @param iter Número de iteraciones a realizar.
     * @return Tiempo en milisegundos que tardó en ejecutarse el método.
     */
    public static long fAtomic(long iter){
        Instant inicio = Instant.now();
        for(long i=0; i<iter; i++){
            nAtomica.incrementAndGet();
        }
        Instant fin = Instant.now();
        Duration tiempo = Duration.between(inicio,fin);
        return(tiempo.toMillis());
    }

    /**
     * Método principal que llama a cada uno de los métodos anteriores con diferentes valores del número de iteraciones,
     * y mide el tiempo que tarda en ejecutarse cada método. Los tiempos de ejecución de cada método se registran en
     * archivos separados (synchronized.txt, reentrant.txt y atomic.txt).
     *
     * @param args Argumentos de la línea de comandos.
     * @throws FileNotFoundException Si no se encuentra algún archivo de salida.
     */
    public static void main(String[] args) throws FileNotFoundException {
        long tiempoSynchronized = 0;
        long tiempoReentrant = 0;
        long tiempoAtomic = 0;
        File fileSynchronized = new File("synchronized.txt");
        PrintWriter writerSynchronized = new PrintWriter(fileSynchronized);
        File fileReentrant = new File("reentrant.txt");
        PrintWriter writerReentrant = new PrintWriter(fileReentrant);
        File fileAtomic = new File("atomic.txt");
        PrintWriter writerAtomic = new PrintWriter(fileAtomic);
        int caso = 0;
        long nIter = 1000;
        while(nIter < 1000000){
            caso = 0;
            System.out.println("-------------------------- N. iteraciones: " + nIter + " --------------------------");
            while (caso != 3) {
                switch (caso) {
                    case 0 -> {
                        tiempoSynchronized = fSynchronized(nIter);
                        System.out.println("Tiempo empleado con cerrojos synchronized: " + tiempoSynchronized + " milisegundos.");
                        writerSynchronized.println(nIter + " " + tiempoSynchronized);
                    }

                    case 1 -> {
                        tiempoReentrant = fReentrant(nIter);
                        System.out.println("Tiempo empleado con cerrojos Reentrant: " + tiempoReentrant + " milisegundos.");
                        writerReentrant.println(nIter + " " + tiempoReentrant);
                    }
                    case 2 -> {
                        tiempoAtomic = fAtomic(nIter);
                        System.out.println("Tiempo empleado con variables Atomic: " + tiempoAtomic + " milisegundos.");
                        writerAtomic.println(nIter + " " + tiempoAtomic);
                    }
                    default -> {
                    }
                }
                caso++;
            }

            nIter+=1000;

        }
        writerSynchronized.close();
        writerReentrant.close();
        writerAtomic.close();

    }
}
