import java.util.concurrent.locks.ReentrantLock;

/**
 *                                  cCRL
 * Clase cuentaCorriente que contiene los métodos con los que operar sobre la cuenta bancaria, con preservación de exclusión mutua.
 * @author Jesús Gómez
 * @version 2.0
 *
 * @see ReentrantLock
 */
public class cCRL {
    int numeroCuenta = 0; // Número de cuenta propio de cada cuenta bancaria
    double saldo = 0; // Saldo disponible en la cuenta bancaria
    ReentrantLock cerrojo;
    /**
     * Constructor de cuentaCorriente que, recibiendo dos parámetros,
     * asigna nuevos valores a "numeroCuenta" y "saldo".
     * @param numero (Nuevo número de cuenta)
     * @param cantidad (Nuevo saldo)
     */
    cCRL(int numero, double cantidad){
        numeroCuenta = numero;
        saldo = cantidad;
        cerrojo = new ReentrantLock();
    }

    /**
     * Método modificador del atributo "saldo"
     * Incrementa el saldo disponible
     * @param cantidad (Nueva cantidad)
     */
    public void deposito(double cantidad){
        cerrojo.lock();
        try{
            saldo += cantidad;
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Método modificador del atributo "saldo"
     * Decrementa el saldo disponible
     * @param cantidad (Nueva cantidad)
     */
    public void reintegro(double cantidad){
        cerrojo.lock();
        try{
            saldo -= cantidad;
        } finally {
            cerrojo.unlock();
        }
    }
    /**
     * Selector de función, dada una operación y cantidad de dinero.
     * Al finalizar cualquier operación, devuelve el nuevo saldo.
     * @param op (Operación a elegir)
     * @param cantidad (Cantidad a añadir o reintegrar)
     * @return saldo (Saldo de la cuenta bancaria)
     */
    public double funcion(int op, double cantidad){
        cerrojo.lock();
        try {
            if(op==0){
                deposito(cantidad);
            }else{
                reintegro(cantidad);
            }
            return saldo;
        }finally {
            cerrojo.unlock();
        }
    }
}
