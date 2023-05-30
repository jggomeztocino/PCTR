import java.util.Arrays;
import java.util.Random;

public class matVectorConcurrente implements Runnable{
    public static final int N = 16;
    private static int [][]A = new int[N][N];
    private static int []b = new int[N];
    private static int []y = new int[N];
    private int id;
    private int sizeAsignado;

    public matVectorConcurrente(int idHebra, int sizeAsignado){
        this.id = idHebra;
        this.sizeAsignado = sizeAsignado;
    }
    public static void rellenarMatrizVector(){
        Random seed = new Random();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = seed.nextInt(10);
                b[i] = seed.nextInt(10);
            }
        }
    }

    public void run(){
        for(int i = (id-1)*sizeAsignado; i < id*sizeAsignado; i++){
            for(int j = 0; j < sizeAsignado; j++){
                y[i] = A[i][j]*b[i];
            }
        }
    }

    public static void main(String[] args) throws Exception{
        int nHebras = 8;
        int size = N/nHebras;
        int resto = N%nHebras;
        Runnable r1 = new matVectorConcurrente(1,size);
        Runnable r2 = new matVectorConcurrente(2,size);
        Runnable r3 = new matVectorConcurrente(3,size);
        Runnable r4 = new matVectorConcurrente(4,size);
        Runnable r5 = new matVectorConcurrente(5,size);
        Runnable r6 = new matVectorConcurrente(6,size);
        Runnable r7 = new matVectorConcurrente(7,size);
        Runnable r8 = new matVectorConcurrente(8,size+resto);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        Thread t5 = new Thread(r5);
        Thread t6 = new Thread(r6);
        Thread t7 = new Thread(r7);
        Thread t8 = new Thread(r8);
        rellenarMatrizVector();
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start(); t6.start(); t7.start(); t8.start();
        t1.join(); t2.join(); t3.join(); t4.join(); t5.join(); t6.join(); t7.join(); t8.join();
        System.out.println(Arrays.toString(y));
    }

}
