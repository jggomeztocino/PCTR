/**
 * Clase que controla la E.M. en el acceso a datos compartidos,
 * mediante synchronized.
 * @author Jesús Gómez
 * @version 1.0
 */
public class arrSeguro extends Thread{

    /** Objeto básico que funcionará
     * como cerrojo para los métodos concurrentes.
     */
    public static final Object cerrojo = new Object();

    /**
     * Vector sobre el que controlar la E.M.
     */
    public static int [] myArray = new int [2];

    /**
     * Atributo que especifica el tipo de hilo.
     */
    int tipoHilo = 0;

    /**
     * Constructor de clase.
     * Especifica el tipo de hilo.
     *
     * @param tipoHilo Tipo de Hilo
     */
    arrSeguro(int tipoHilo){
        this.tipoHilo = tipoHilo;
    }

    /** Método heredado de Thread,
     * que implementa el comportamiento del hilo.
     * @see Thread
     */
    public void run(){
        synchronized (cerrojo){
            if(tipoHilo==1){
                myArray[0]++;
            }
            if(tipoHilo==2){
                myArray[1]--;
            }
        }
    }

    /**
     * Hilo principal que demuestra el funcionamiento del programa
     * @param args No se usa
     */
    static public void main(String [] args) throws InterruptedException {

        /**
         * Inicialización del vector
         */
        myArray[0] = -100; myArray[1] = 100;

        arrSeguro hiloSuma = null;
        arrSeguro hiloResta = null;
        
        for(int i = 0; i < 100; i++){
            hiloSuma = new arrSeguro(1);
            hiloResta = new arrSeguro(2);
            hiloSuma.start(); hiloResta.start();
        }
        
        hiloSuma.join();
        hiloResta.join();

        System.out.println("Dato 1. Esperado: 0, Resultado: " + myArray[0]);
        System.out.println("Dato 2. Esperado: 0, Resultado: " + myArray[1]);

    }
}
