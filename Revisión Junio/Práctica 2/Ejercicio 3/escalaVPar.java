import java.util.Arrays;

public class escalaVPar implements Runnable {
    private static double vector[] = new double[1000000];
    private static int factorEscalado = 3;
    private int inicio;
    private int fin;
    public escalaVPar(int inicio, int fin){
        this.inicio=inicio;
        this.fin=fin;
    }

    @Override
    public void run(){
        for(int i = inicio; i < fin; i++){
            vector[i] = i;
            vector[i] = vector[i]*3.14;
        }
    }

    public static void printVector(){
        System.out.println(Arrays.toString(vector));
    }

    public static void main(String[] args) throws Exception{
        Runnable r1 = new escalaVPar(0,100000);
        Runnable r2 = new escalaVPar(100000,200000);
        Runnable r3 = new escalaVPar(200000,300000);
        Runnable r4 = new escalaVPar(300000,400000);
        Runnable r5 = new escalaVPar(400000,500000);
        Runnable r6 = new escalaVPar(500000,600000);
        Runnable r7 = new escalaVPar(600000,700000);
        Runnable r8 = new escalaVPar(700000,800000);
        Runnable r9 = new escalaVPar(800000,900000);
        Runnable r10 = new escalaVPar(900000,1000000);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        Thread t5 = new Thread(r5);
        Thread t6 = new Thread(r6);
        Thread t7 = new Thread(r7);
        Thread t8 = new Thread(r8);
        Thread t9 = new Thread(r9);
        Thread t10 = new Thread(r10);
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start(); t6.start(); t7.start(); t8.start(); t9.start(); t10.start();
        t1.join(); t2.join(); t3.join(); t4.join(); t5.join(); t6.join(); t7.join(); t8.join(); t9.join(); t10.join();
        printVector();
    }
}
