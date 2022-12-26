import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Clase que implementará una versión paralela
 * del método de Monte Carlo.
 * @author Jesús Gómez
 * @version 1.0
 * @see Callable
 */
public class integCallable implements Callable<Integer> {

    float coordenada_x = 0f;
    float coordenada_y = 0f;
    int debajo = 0;
    static Random seed = new Random();

    /**
     * Computa un resultado, o lanza una excepción si no consigue hacerlo.
     *
     * @return resultado computado
     * @throws Exception si no computa el resultado
     */
    @Override
    public Integer call() throws Exception {
        coordenada_x = seed.nextFloat();
        coordenada_y = seed.nextFloat();
        if(coordenada_y<=Math.cos(coordenada_x)){
            debajo++;
        }
        return(debajo);
    }

    /**
     * Hilo principal donde se demostrará el funcionamiento implementado.
     * @param args No se usa
     * @throws ExecutionException Lanzada por Future.get()
     * @throws InterruptedException Lanzada por Future.get()
     * @see Callable
     * @see Future
     * @see ExecutionException
     * @see InterruptedException
     */
    public static void main (String[] args) throws ExecutionException, InterruptedException {
        int nIteraciones = 100;
        int resultado = 0;
        List<Future<Integer>> resultadoAcumulativo = Collections.synchronizedList(new ArrayList<Future<Integer>>());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(nIteraciones,nIteraciones,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue < Runnable >());
        for(int i = 0; i < nIteraciones; i++){
            resultadoAcumulativo.add(executor.submit(new integCallable()));
        }
        for(Future<Integer> iterador:resultadoAcumulativo){
            try{
                resultado+=iterador.get();
            }catch(CancellationException | ExecutionException | InterruptedException ignored){}
        }
        executor.shutdown();
        while(!executor.isTerminated());
        System.out.println("Integral aproximada cos(x) en " + nIteraciones + " iteraciones: " + (double)resultado/nIteraciones);
    }
}
