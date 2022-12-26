/**
 * Clase que demuestra la funcionalidad de Lectores-Escritores,
 * implementada en "lectorEscritor.java".
 * @see lectorEscritor
 * @see recurso
 * @author Jesús Gómez
 * @version 1.0
 */
public class usalectorEscritor extends Thread{
    static recurso r = new recurso();
    static lectorEscritor le = new lectorEscritor();
    static long data = 0;
    static long n = 1000000;

    int tipoHilo;

    /**
     * Constructor de clase.
     * Especificará el tipo de hilo a construir.
     * @param tipoHilo Tipo de Hilo
     */
    usalectorEscritor(int tipoHilo){
        this.tipoHilo = tipoHilo;
    }

    /**
     * Tarea Lectora.
     * Leerá 'n' veces el recurso compartido.
     * @see recurso
     * @see lectorEscritor
     */
    void tareaLectora() throws InterruptedException {
        for(long i = 0; i<n; i++){
            le.iniciaLectura();
            data = r.observer();
            le.acabarLectura();
        }
    }

    /**
     * Tarea Escritoria.
     * Actualizará 'n' veces el recurso compartido.
     * @see recurso
     * @see lectorEscritor
     */
    void tareaEscritora() throws InterruptedException {
        for(long i = 0; i<1000000; i++){
            le.iniciarEscritura();
            r.inc();
            le.acabarEscritura();
        }
    }

    /**
     * Método que define el comportamiento de los hilos.
     * @see Thread
     */
    public void run(){
        switch (tipoHilo) {
            case 0 -> {
                try {
                    tareaEscritora();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            case 1 -> {
                try {
                    tareaLectora();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Hilo principal, donde se demostrará el comportamiento de los hilos
     * @param args No usado
     * @throws InterruptedException Lanzado por los métodos .join()
     */
    public static void main(String[] args) throws InterruptedException {

        Thread h0 = new usalectorEscritor(0);
        Thread h1 = new usalectorEscritor(1);
        Thread h2 = new usalectorEscritor(0);
        Thread h3 = new usalectorEscritor(1);
        Thread h4 = new usalectorEscritor(0);
        Thread h5 = new usalectorEscritor(1);
        h0.start(); h1.start(); h2.start(); h3.start(); h4.start(); h5.start();
        h0.join(); h1.join(); h2.join(); h3.join(); h4.join(); h5.join();
        r.observer();
    }
}
