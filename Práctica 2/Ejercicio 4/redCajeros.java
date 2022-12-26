/**
 * redCajeros
 * Clase redCajeros, que demuestra el funcionamiento de la red de cajeros.
 * Lanzará una serie de hilos que operarán al mismo tiempo sobre una misma cuenta bancaria.
 * @see Thread
 * @see Runnable
 * @see cuentaCorriente
 * @see cajero
 *
 * @author Jesús Gómez
 * @version 1.0
 */
public class redCajeros {
    /**
     * Hilo principal, encargado de lanzar los demás hilos.
     * @param args (No usado)
     */
    public static void main (String [] args){
        cuentaCorriente cuenta = new cuentaCorriente(69,0); // Creamos una cuenta corriente, compartida por todos los hilos

        /** Se lanzará un número determinado de hilos (en este caso 2*10000 = 20000).
        * La mitad de hilos incrementará el saldo y la otra mitad los reducirá
         */
        for (int i = 0; i<10000; i++){
            new Thread(new cajero(0,10000,cuenta)).start(); // Incrementará el saldo en 1000 unidades
            new Thread(new cajero(1,10000,cuenta)).start(); // Decrementará el saldo en 1000 unidades
        }
    }
}
