import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Implementación paralela de un programa que calcule números perfectos dentro de un rango.
 * Un número natural es perfecto cuando es igual a la suma de sus divisores propios (por ejemplo, 6 y 28 son perfectos).
 */
public class numPerfectosParalelo implements Callable<Integer>{
    /**
     * Ecuación de Subramanian.
     */
    static int Nnld = Runtime.getRuntime().availableProcessors();
    static float Cb = 0f; // Es un problema de cálculo aritmético.
    static int Nt = (int)(Nnld/(1-Cb));

    /**
     * Variables propia de cada hebra
     */
    private int id;
    private int inicio;
    private int fin;


    /**
     * Constructor de la clase
     * @param id ID único de hebra
     * @param inicio Inicio del rango individual
     * @param fin Fin del rango individual
     */
    public numPerfectosParalelo(int id, int inicio, int fin){
        System.out.println("Hebra " + id + ". Inicio: " + inicio + ", Fin: " + fin);
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Integer call() throws Exception {
        int numNumPerfectos = 0;
        int suma;
        for (int i = inicio; i < fin; i++){
            if(i > 1){ // 0 y 1 no son números perfectos.
                suma = 0;
                for(int j = 1; j < i; j++){
                    if(i%j==0){
                        suma+=j;
                    }
                }
                if(suma == i){
                    System.out.println("------------------------------> HEBRA " + id +" DICE: " + i + " es un numero perfecto!");
                    numNumPerfectos++;
                }
            }
        }
        return numNumPerfectos;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Número de hilos disponibles: " +  Nnld);
        ExecutorService executor = Executors.newFixedThreadPool(Nnld);
        List<Future<Integer>> resultado = new ArrayList<>();
        //int inicio = Integer.parseInt(args[0]);
        int inicio = 0;
        //int fin = Integer.parseInt(args[1]);
        int fin = 30;
        int rango = fin - inicio;
        int size = rango/Nnld;
        int inicioActual = 0;
        int finActual = size;
        System.out.println("Rango a analizar: [" + inicio + "," + fin + "]");
        System.out.println("Tamaño del rango: " + rango);
        System.out.println("Tamaño del rango individual: " + size);
        for(int i = 0; i < Nnld; i++){
            resultado.add(executor.submit(new numPerfectosParalelo(i+1,inicioActual, finActual)));
            inicioActual += size;
            finActual += size;
            if(i+1 == Nnld-1){
                finActual += rango%Nnld;
            }
        }
        executor.shutdown();
        while (!executor.isTerminated());
        int numNumPerfectos = 0;
        for(Future<Integer> elemento : resultado){
            numNumPerfectos += elemento.get();
        }
        System.out.println("Obteniendo el resultado parcial de cada hebra...");
        System.out.println("Número de números perfectos: " + numNumPerfectos);
    }
}