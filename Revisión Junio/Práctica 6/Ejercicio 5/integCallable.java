import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class integCallable implements Callable <Integer>{
    private static Random seed = new Random();
    private double coordenadaX = 0.0;
    private double coordenadaY = 0.0;
    public Integer call(){
        coordenadaX = seed.nextDouble();
        coordenadaY = seed.nextDouble();
        if(coordenadaY <= Math.cos(coordenadaX)){
            return 1;
        }
        return 0;
    }

    public static void main (String [] args) throws Exception{
        int n = 0;

        while(n<1){
            System.out.print("Introduzca el número de iteraciones deseadas: ");
            Scanner s = new Scanner(System.in);
            n = s.nextInt();
        }

        ExecutorService pool = Executors.newFixedThreadPool(n);
        List<Future<Integer>> resultadosParciales = Collections.synchronizedList(new ArrayList<Future<Integer>>());
        for(int i = 0; i < n; i++){
            resultadosParciales.add(pool.submit(new integCallable()));
        }
        pool.shutdown();

        int contador_exitos = 0;
        for(int i = 0; i < n; i++){
            contador_exitos += resultadosParciales.get(i).get();
        }

        double integral = (double)contador_exitos/n;
        System.out.print("Integral aproximada: ");
        System.out.println(integral);
    }
}

