public class deadlock extends Thread{
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    private int idHebra;

    public deadlock(int id){
        this.idHebra = id;
        this.start();
    }

    private void f1(){
        synchronized (lock1){
            synchronized (lock2){
                System.out.println("Hola desde la hebra: " + idHebra);
            }
        }
    }

    private void f2(){
        synchronized (lock2){
            synchronized (lock1){
                System.out.println("Hola desde la hebra: " + idHebra);
            }
        }
    }

    private void f3(){
        synchronized (lock1){
            synchronized (lock2){
                System.out.println("Hola desde la hebra: " + idHebra);
            }
        }
    }

    public void run(){
        if(idHebra==1) f1();
        if(idHebra==2) f2();
        if(idHebra==3) f3();
    }

    public static void main(String[] args) throws InterruptedException{
        for(int i = 0; i < 1000000; i++){
            System.out.println("---- INTENTO " + i + " ----");
            Thread t1 = new deadlock(1);
            Thread t2 = new deadlock(2);
            Thread t3 = new deadlock(3);
            t1.join(); t2.join(); t3.join();
        }
    }
}
