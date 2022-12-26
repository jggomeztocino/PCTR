import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *                                  cCRL
 * Clase cuentaCorriente que contiene los métodos con los que operar sobre la cuenta bancaria, con preservación de exclusión mutua.
 * @author Jesús Gómez
 * @version 2.0
 *
 * @see Semaphore
 */
public class ccSem {
    int numeroCuenta = 0; // Número de cuenta propio de cada cuenta bancaria
    double saldo = 0; // Saldo disponible en la cuenta bancaria
    Semaphore semaforo;

    /**
     * Constructor de cuentaCorriente que, recibiendo dos parámetros,
     * asigna nuevos valores a "numeroCuenta" y "saldo".
     * @param numero (Nuevo número de cuenta)
     * @param cantidad (Nuevo saldo)
     */
    ccSem(int numero, double cantidad){
        numeroCuenta = numero;
        saldo = cantidad;
        semaforo = new Semaphore(1);
    }

    /**
     * Método modificador del atributo "saldo"
     * Incrementa el saldo disponible
     * @param cantidad (Nueva cantidad)
     */
    public void deposito(double cantidad) throws InterruptedException {
        saldo += cantidad;
    }

    /**
     * Método modificador del atributo "saldo"
     * Decrementa el saldo disponible
     * @param cantidad (Nueva cantidad)
     */
    public void reintegro(double cantidad) throws InterruptedException {
        saldo -= cantidad;
    }

    /**
     * Selector de función, dada una operación y cantidad de dinero.
     * Al finalizar cualquier operación, devuelve el nuevo saldo.
     * @param op (Operación a elegir)
     * @param cantidad (Cantidad a añadir o reintegrar)
     * @return saldo (Saldo de la cuenta bancaria)
     */
    public double funcion(int op, double cantidad) throws InterruptedException {
        semaforo.acquire();
        try {
            if(op==0){
                deposito(cantidad);
            }else{
                reintegro(cantidad);
            }
            return saldo;
        } finally {
            semaforo.release();
        }
    }
}
