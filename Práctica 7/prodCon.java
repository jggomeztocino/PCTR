/**
 * Clase que implementará la funcionalidad Productor-Consumidor,
 * mediante el uso de un monitor.
 * @author Jesús Gómez
 * @version 1.0
 */
public class prodCon {
    /**
     * Para construir un monitor,
     * debemos encapsular todos los datos como atributos privados.
     */
    private static int N;
    private static int [] B;
    private static int In_Ptr;
    private static int Out_Prt;
    private static int Count;
    private static boolean Not_Full;
    private static boolean Not_Empty;

    /**
     * Constructor de clase.
     * Contiene el código de inicialización de atributos
     * (Característica del monitor)
     */
    public prodCon() {
        N = 5;
        B = new int [N];
        In_Ptr = 0;
        Out_Prt = 0;
        Count = 0;
        Not_Full = true;
        Not_Empty = false;
    }

    /**
     * Método producir de la relación Productor-Consumidor.
     * Declarado como synchronized para preservar la exclusión mutua.
     * (Característica del monitor)
     */
    public synchronized void producir(int I){
        while(!Not_Full){
            try{
                wait();
            }catch(InterruptedException ignored){}
        }
        System.out.println("Productor: " + Thread.currentThread().getName() + " produciendo el dato: " + I + " ...");
        B[In_Ptr]= I;
        In_Ptr = (In_Ptr + 1) % N;
        Count++;
        if(Count==N){
            Not_Full = false;
        }
        Not_Empty = true;
        notifyAll();
    }

    /**
     * Método consumir de la relación Productor-Consumidor.
     * Declarado como synchronized para preservar la exclusión mutua.
     * (Característica del monitor)
     */
    public synchronized int consumir(){
        while(!Not_Empty){
            try{
                wait();
            }catch(InterruptedException ignored){};
        }
        int I = B[Out_Prt];
        B[Out_Prt] = -1;
        System.out.println("Consumidor: " + Thread.currentThread().getName() + " consumiendo el dato: " + I + " ...");
        Out_Prt = (Out_Prt + 1) % N;
        Count--;
        if(Count==0){
            Not_Empty = false;
        }
        Not_Full = true;
        notifyAll();
        return I;
    }
}
