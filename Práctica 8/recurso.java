/**
 * Clase gestiona el recurso compartido,
 * usado en "lectorEscritor.java".
 * @see lectorEscritor
 * @see usalectorEscritor
 * @author Jesús Gómez
 * @version 1.0
 */
public class recurso {
    private static long n = 0; // Dato compartido

    /**
     * Función que incrementa el valor del recurso compartido
     */
    public static void inc(){
        System.out.println("Escritor: " + Thread.currentThread().getName() + ", aumenta el recurso en 1 unidad.");
        n++;
    }

    /**
     * Función que devuelve el recurso compartido.
     * @return n [Recurso Compartido]
     */
    public static long observer(){
        System.out.println("Lector: " + Thread.currentThread().getName() + ", leyendo el dato: " + n);
        return n;
    }
}
