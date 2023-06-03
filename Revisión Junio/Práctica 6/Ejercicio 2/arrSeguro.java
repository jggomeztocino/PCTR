import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class arrSeguro extends Thread{
    private int idHebra;

    public arrSeguro(int id){
        this.idHebra = id;
        this.start();
    }
    private static Object cerrojo = new Object();
    public static int [] arraySeguro = new int[50];
    public static int [] array = new int[50];
    private static void sobreescribirMatrizSeguro(int id){
        synchronized (cerrojo){
            for(int i = 0; i < 50; i++){
                arraySeguro[i] = id;
            }
        }
    }
    private static void sobreescribirMatriz(int id){
        for(int i = 0; i < 50; i++){
            array[i] = id;
        }
    }
    public void run(){
        sobreescribirMatriz(this.idHebra);
        sobreescribirMatrizSeguro(this.idHebra);
    }

    public static void main(String[] args) {
        Thread t1 = new arrSeguro(1);
        Thread t2 = new arrSeguro(2);
        Thread t3 = new arrSeguro(3);
        Thread t4 = new arrSeguro(4);
        Thread t5 = new arrSeguro(5);
        Thread t6 = new arrSeguro(6);
        Thread t7 = new arrSeguro(7);
        Thread t8 = new arrSeguro(8);
        Thread t9 = new arrSeguro(9);
        Thread t10 = new arrSeguro(10);
        System.out.println("Sin usar cerrojos: " + Arrays.toString(array));
        System.out.println("Usando cerrojos: " + Arrays.toString(arraySeguro));
    }
}
