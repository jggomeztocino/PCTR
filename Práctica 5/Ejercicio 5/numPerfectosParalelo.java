import java.util.Date;
import java.util.concurrent.*;

/**
 * @author Jesús Gómez
 * @version 1.0
 */
public class numPerfectosParalelo implements Callable<Integer>{
    /**
     * Ecuación de Subramanian.
     */
    static int nNuc = Runtime.getRuntime().availableProcessors();
    static float Cb = 0f;
    static int poolSize = (int)(nNuc/(1-Cb));
    static int contador = 0;

    /**
     * Variables de rango globales
     */
    static int rangoInicio = 0;
    static int rangoFin = 0;
    static int nNumTotal = 0;

    /**
     * Variables de rango internas
     */
    int inicio = 0;
    int fin = 0;
    int nNum = 0;


    /**
     * Constructor de clase.
     * Parametriza los rangos individuales.
     * @param i Número de hilos
     */
    numPerfectosParalelo(int i){
        nNum = nNumTotal/poolSize;
        inicio = i * nNum;
        if(i+1 == poolSize) {
            nNum += (rangoFin-rangoInicio) % poolSize;
        }
        fin = inicio + nNum;
    }

    /**
     * Función que devuelve los números perfectos existentes en un rango dado.
     * @return contador Devuelve el nº de números perfectos.
     */
    public int nNumPerfectos(){
        int divisores = 0;
        for (int i = inicio; i < fin; i++){
            divisores = 0;
            for(int j = 1; j < i; j++){
                if(i%j == 0){
                    divisores+=j;
                }
            }
            if (divisores==i){
                contador++;
            }
            if(i==1){ // 1 no es un número perfecto
                contador--;
            }
        }
        return contador;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        return nNumPerfectos();
    }

    /**
     * Hilo principal, prueba del programa desarrollado.
     * @param args Donde irán los argumentos pasados por la línea de comandos.
     * @see Executors
     * @see ExecutorService
     * @see Date
     */
    public static void main(String[] args) {
        rangoInicio = Integer.parseInt(args[0]);
        rangoFin = Integer.parseInt(args[1]);
        nNumTotal = rangoFin - rangoInicio;
        int numNumerosPerfectos = 0;

        /**
         * Preparación y activación del cronómetro.
         */
        Date d = new Date();
        long inicCronom = System.currentTimeMillis(); // Se prepara el cronometro
        d.setTime(inicCronom); // Se activa el cronómetro

        /**
         * Bloque de excepción, implementado a raíz del método .get()
         */
        try {
            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
            Future<Integer> resultado = null;
            for (int i = 0; i < poolSize; i++) {
                resultado = executor.submit(new numPerfectosParalelo(i));
            }
            executor.shutdown();
            while (!executor.isTerminated());
            numNumerosPerfectos = resultado.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Excepción capturada.");
        }

        /**
         * Parada del cronómetro y muestra del tiempo.
         */
        long finCronom = System.currentTimeMillis(); // Se para el cronómetro
        d.setTime(finCronom);
        System.out.println("Nº de números perfectos en el rango [" + rangoInicio + "-" + rangoFin + "]: " + numNumerosPerfectos);
        System.out.println("Nº hebras: " + nNuc + ". Tiempo: "+ (finCronom - inicCronom) + " ms.");
    }
}