import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase que demuestra el comportamiento de heterogénea,
 * pudiendo observar la dualidad del sincronismo en la clase.
 * @author Jesús Gómez
 * @version 1.0
 * @see heterogenea
 */
public class usaheterogenea {
    /**
     * @see Executors
     * @see ExecutorService
     * @param args No se usa
     */
    public static void main (String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 100000; i++){
            executor.execute(new heterogenea(i%2));
        }

        executor.shutdown();
        while(!executor.isTerminated());

        System.out.println("Dato n. Esperado: 50000, Resultado: " + heterogenea.n);
        System.out.println("Dato m. Esperado: 50000, Resultado: " + heterogenea.m);
    }
}
