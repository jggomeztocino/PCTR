import java.util.Random;

/**
 * Clase que implementarán la vida útil de los hilos
 * y demostrarán el funcionamiento de la relación implementada.
 * @author Jesús Gómez
 * @version 1.0
 * @see Thread
 * @see Random
 * @see prodCon
 */
public class usaprodCon extends Thread{
    static prodCon buffer = new prodCon(); // Monitor
    int tipoHilo;
    Random seed = new Random();

    /**
     * Constructor de clase
     * @param tipoHilo Tipo de Hilo creado (1 productor, 2 consumidor)
     */
    usaprodCon(int tipoHilo){
        this.tipoHilo=tipoHilo;
    }

    /**
     * Método heredado de Thread,
     * que implementa el comportamiento de los hilos.
     * @see Thread
     */
    @Override
    public void run() {
        if(tipoHilo==1){
            while(true){
                buffer.producir(seed.nextInt(1000));
                try{
                    sleep(100);
                } catch (InterruptedException ignore) {}
            }
        }
        if(tipoHilo==2){
            while(true){
                buffer.consumir();
                try{
                    sleep(100);
                } catch (InterruptedException ignore) {}
            }
        }
    }

    /**
     * Hilo principal donde se lanzan los demás hilos.
     * @param args No se usa
     * @throws InterruptedException Lanzada por los métodos .join()
     */
    public static void main(String[] args) throws InterruptedException {
        usaprodCon productor = new usaprodCon(1);

        // Descomentar en el caso de un consumidor, varios productores
        usaprodCon productor2 = new usaprodCon(1);
        usaprodCon productor3 = new usaprodCon(1);
        usaprodCon productor4 = new usaprodCon(1);
        usaprodCon productor5 = new usaprodCon(1);

        usaprodCon consumidor = new usaprodCon(2);

        // Descomentar en el caso de un productor, varios consumidores
        /*
        usaprodCon consumidor2 = new usaprodCon(2);
        usaprodCon consumidor3 = new usaprodCon(2);
        usaprodCon consumidor4 = new usaprodCon(2);
        usaprodCon consumidor5 = new usaprodCon(2);
         */

        productor.start(); consumidor.start();

        // Descomentar en el caso de un consumidor, varios productores
        productor2.start(); productor3.start(); productor4.start(); productor5.start();

        // Descomentar en el caso de un productor, varios consumidores
        //consumidor2.start(); consumidor3.start(); consumidor4.start(); consumidor5.start();

        productor.join(); consumidor.join();

        // Descomentar en el caso de un consumidor, varios productores
        productor2.join(); productor3.join(); productor4.join(); productor5.join();

        // Descomentar en el caso de un productor, varios consumidores
        //consumidor2.join(); consumidor3.join(); consumidor4.join(); consumidor5.join();

    }
}
