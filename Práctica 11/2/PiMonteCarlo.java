import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**                                 PiMonteCarlo
 * Clase que implementa un método para calcular aproximaciones de pi mediante el método de Monte Carlo.
 * @author Jesús Gómez
 * @version 1.0
 */
public class PiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
    // Generador de números aleatorios
    private final Random random = new Random();
    // Contador de puntos que caen dentro del círculo
    private final AtomicInteger puntosEnCirculo = new AtomicInteger(0);
    // Contador de puntos totales
    private final AtomicInteger puntosTotales = new AtomicInteger(0);

    /**
     * Constructor de la clase.
     * @throws RemoteException Si hay un problema al crear el objeto remoto.
     */
    public PiMonteCarlo() throws RemoteException {
        super();
    }

    /**
     * Reinicia el contador de puntos y la aproximación de pi.
     *
     * @throws RemoteException Si hay un problema al acceder al método remoto.
     */
    public void reset() throws RemoteException {
        puntosEnCirculo.set(0);
        puntosTotales.set(0);
    }

    /**
     * Agrega una cantidad determinada de puntos al cálculo de pi y devuelve la aproximación actual.
     *
     * @param nPuntos La cantidad de puntos a agregar al cálculo.
     * @throws RemoteException Si hay un problema al acceder al método remoto.
     */
    public void masPuntos(int nPuntos) throws RemoteException {
        double x, y;
        for (int i = 0; i < nPuntos; i++) {
            x = random.nextDouble();
            y = random.nextDouble();
            // Si el punto cae dentro del círculo, aumentamos el contador
            if ((y * y) < (1 - (x * x))) {
                puntosEnCirculo.incrementAndGet();
            }
        }
        // Incrementamos el contador de puntos totales
        puntosTotales.addAndGet(nPuntos);
    }

    /**
     * Devuelve la aproximación actual de pi.
     *
     * @return La aproximación actual de pi.
     * @throws RemoteException Si hay un problema al acceder al método remoto.
     */
    public double aproxActual() throws RemoteException {
        return (double) 4 * puntosEnCirculo.get() / puntosTotales.get();
    }

    /**
     * Método principal que crea el objeto remoto y lo registra en el registro RMI.
     *
     * @param args Los argumentos de la línea de comandos. No se utilizan en este caso.
     * @throws Exception Si hay un problema al crear el objeto remoto o registrarlo en el registro RMI.
     */
    public static void main(String[] args) throws Exception {
        iPiMonteCarlo servidor = new PiMonteCarlo();
        Registry registro = LocateRegistry.createRegistry(1099);
        Naming.bind("Servidor", servidor);
        System.out.println("Sevidor preparado");
    }
}

