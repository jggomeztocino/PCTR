public class Usa_tareaRunnable{
    public static void main(String[] args) throws Exception{
        tareaRunnable r1 = new tareaRunnable(1000000, 0);
        tareaRunnable r2 = new tareaRunnable(1000000, 1);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(tareaRunnable.getN());
    }
}
