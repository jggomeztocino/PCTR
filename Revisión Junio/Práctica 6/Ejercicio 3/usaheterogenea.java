import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaheterogenea implements Runnable{
    private static Object cerrojo = new Object();
    public static int n = 0;
    public static int m = 0;
    private static void sobreescribirN(){
        synchronized (cerrojo){
            n++;
        }
    }
    private static void sobreescribirM(){
        m++;
    }
    public void run(){
        sobreescribirN();
        sobreescribirM();
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0; i<1000000; i++){
            pool.execute(new usaheterogenea());
        }
        pool.shutdown();
        while(!pool.isTerminated());
        System.out.println("Sin usar cerrojos: " + m);
        System.out.println("Usando cerrojos: " + n);
    }
}
