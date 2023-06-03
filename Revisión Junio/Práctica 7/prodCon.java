public class prodCon {
    private int InPtr = 0;
    private int OutPtr = 0;
    private int maxSpaces;
    private int Spaces;
    private int Elements = 0;
    private int [] Buffer;

    //private boolean empty, full;

    public prodCon(int n){
        maxSpaces = n;
        Spaces = n;
        Buffer = new int[n];
    }

    public synchronized void producir(int I){
        while(Spaces == 0){
            try{
                wait();
            }catch (InterruptedException ignored){}
        }
        Buffer[InPtr] = I;
        Elements++; Spaces--;
        InPtr = (InPtr+1) % maxSpaces;
        System.out.println("-------------------- PRODUCCIÓN --------------------");
        System.out.println("Productor " + Thread.currentThread().getName() + " produce el elemento " + I);
        System.out.println("Elementos disponibles: " + Elements);
        //empty = (false);
        //full = (Spaces == 0);
        notifyAll();
    }

    public synchronized void consumir(){
        while(Elements == 0){
            try{
                wait();
            }catch (InterruptedException ignored){}
        }

        int I = Buffer[OutPtr];
        Buffer[OutPtr] = -1;
        Elements--; Spaces++;
        OutPtr = (OutPtr+1) % maxSpaces;
        System.out.println("-------------------- CONSUMICIÓN --------------------");
        System.out.println("Consumidor " + Thread.currentThread().getName() + " consume el elemento " + I);
        System.out.println("Elementos disponibles: " + Elements);
        //full = false;
        //empty = (Elements == 0);
        notifyAll();
    }
}
