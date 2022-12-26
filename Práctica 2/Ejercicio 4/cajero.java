/**
 *                                     cajero
 * Clase cajero, que implementa la interfaz Runnable.
 * Define el comportamiento de los hilos que se creen de esta clase (método run())
 * Opera sobre una cuenta bancaria.
 * @see cuentaCorriente
 * @see redCajeros
 * @see Runnable
 * @see Thread
 *
 * @author Jesús Gómez
 * @version 1.0
 */
public class cajero implements Runnable {
    int operacion = 0; // Tipo de operación que va a realizar el cajero (1. Depósito, 2. Reintegro)

    double cantidadTotal; // Cantidad de dinero a depositar o reintegrar.

    cuentaCorriente cuentaCliente; // Objeto de la clase cuentaCorriente. Especifica la cuenta sobre la que trabajar.

    /**
     * Constructor de cajero, que recibiendo un tipo de operacion,
     * una cantidad y una cuenta sobre la que operar,
     * asigna a las variables "operacion", "cantidadTotal" y "cuentaCliente" unos nuevos valores.
     * @param op (Tipo de operación)
     * @param cantidad (Cantidad a depositar o reintegrar)
     * @param cuenta (Número de cuenta bancaria)
     */
    cajero(int op, double cantidad, cuentaCorriente cuenta){
        operacion = op;
        cantidadTotal = cantidad;
        cuentaCliente = cuenta;
    }

    /**
     * Método implementado por la interfaz Runnable, heredado por la clase Thread.
     * Define el comportamiento del hilo a lanzar.
     * @see Runnable
     * @see Thread
     */
    public void run(){
        // Imprime por pantalla a la vez que llama a la función que ejecuta la operación correspondiente.
        // La función devuelve el nuevo saldo.
        System.out.println("Nuevo saldo: " + cuentaCliente.funcion(operacion,cantidadTotal));
    }
}
