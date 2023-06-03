public class usalectorEscritor extends Thread{
    private static recurso r = new recurso();
    private static lectorEscritor le = new lectorEscritor();

    private boolean escritor;

    public usalectorEscritor(boolean esEscritor){
        escritor = esEscritor;
        this.start();
    }

    private void tareaLectora(){
        long data = -1;
        //tarea lectora...
        for(long i=0; i<1000000; i++){
            le.iniciaLectura();
            data = r.observer();
            le.acabarLectura();
            System.out.println("La hebra lectora " + Thread.currentThread().getName() + " leyó el dato " + data + ".");
        }
    }

    private void tareaEscritora(){
        long data = -1;
        //tarea escritora...
        for(long i=0; i<1000000; i++){
            le.iniciarEscritura();
            r.inc();
            data = r.observer();
            le.acabarEscritura();
            System.out.println("La hebra escritora " + Thread.currentThread().getName() + " escribió el dato " + data + ".");
        }
    }

    public void run(){
        if(escritor) tareaEscritora();
        else tareaLectora();
    }

    public static void main(String[] args) {
        Thread escritor1 = new usalectorEscritor(true);
        Thread escritor2 = new usalectorEscritor(true);
        Thread lector1 = new usalectorEscritor(false);
        Thread lector2 = new usalectorEscritor(false);
        try{
            escritor1.join(); escritor2.join();
            lector1.join(); lector2.join();
        }catch(InterruptedException ignore){}
    }
}
