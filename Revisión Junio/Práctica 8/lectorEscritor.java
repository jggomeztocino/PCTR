public class lectorEscritor {
    private int lectores;
    private boolean escribiendo;

    public lectorEscritor(){
        lectores = 0;
        escribiendo = false;
    }
    public synchronized void iniciaLectura(){
        while(escribiendo){
            try{
                wait();
            }catch(InterruptedException ignore){}
        }
        lectores++;
        notifyAll();
    }

    public synchronized void acabarLectura(){
        lectores--;
        if(lectores == 0){
            notifyAll();
        }
    }

    public synchronized void iniciarEscritura(){
        while(lectores!=0 || escribiendo){
            try{
                wait();
            }catch(InterruptedException ignore){}
        }
        escribiendo = true;
    }

    public synchronized void acabarEscritura(){
        escribiendo = false;
        notifyAll();
    }
}
