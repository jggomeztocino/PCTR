import java.util.Date;

/**
 * @author Jesús Gómez
 * @version 1.0
 */
public class numPerfectos {
    /**
     * Variables de rango
     */
    static int inicio = 0;
    static int fin = 0;

    /**
     * Función que devuelve los números perfectos existentes en un rango dado.
     * @return contador Devuelve el nº de números perfectos.
     */
    public static int nNumPerfectos(){
        int contador = 0;
        int divisores = 0;
        for (int i = inicio; i <= fin; i++){
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
     * Hilo principal, prueba del programa desarrollado.
     * @param args Se pasa por la consola de comandos el inicio y el final.
     */
    public static void main(String[] args) {
        inicio = Integer.parseInt(args[0]);
        fin = Integer.parseInt(args[1]);
        int numNumerosPerfectos = 0;
        /**
         * Preparación y activación del cronómetro.
         */
        Date d = new Date();
        long inicCronom = System.currentTimeMillis(); // Se prepara el cronometro
        d.setTime(inicCronom); // Se activa el cronómetro

        numNumerosPerfectos = nNumPerfectos();

        /**
         * Parada del cronómetro y muestra del tiempo y del número calculado.
         */
        long finCronom = System.currentTimeMillis(); // Se para el cronómetro
        d.setTime(finCronom);

        System.out.println("Nº de números perfectos en el rango [" + inicio + "-" + fin + "]: " + numNumerosPerfectos);
        System.out.println("Nº hebras: 1. Tiempo: "+ (finCronom - inicCronom) + " ms.");
    }
}
