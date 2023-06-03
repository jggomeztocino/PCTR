import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class barrera implements Runnable{
    private static CyclicBarrier b = new CyclicBarrier(3);
    public void run(){
        System.out.println("Hebra " + Thread.currentThread().getName() + " llegando a la barrera...");
        try{
            b.await();
        }catch (InterruptedException | BrokenBarrierException ignore){}
        System.out.println("Hebra " + Thread.currentThread().getName() + " saliendo de la barrera...");
    }

    public static void main(String[] args) {
        System.out.println("Hebra " + Thread.currentThread().getName() + " llegando a la barrera...");
        new Thread(new barrera()).start();
        new Thread(new barrera()).start();
        try{
            b.await();
        }catch (InterruptedException | BrokenBarrierException ignore){}
        System.out.println("Hebra " + Thread.currentThread().getName() + " saliendo de la barrera...");
    }
}
