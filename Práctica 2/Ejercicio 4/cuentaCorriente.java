/**
 *                                  cuentaCorriente
 * Clase cuentaCorriente que contiene los métodos con los que operar sobre la cuenta bancaria.
 * @author Jesús Gómez
 * @version 1.0
 * @see cajero
 * @see redCajeros
 */
public class cuentaCorriente {
    int numeroCuenta = 0; // Número de cuenta propio de cada cuenta bancaria

    double saldo = 0; // Saldo disponible en la cuenta bancaria

    /**
     * Constructor de cuentaCorriente que, recibiendo dos parámetros,
     * asigna nuevos valores a "numeroCuenta" y "saldo".
     * @param numero (Nuevo número de cuenta)
     * @param cantidad (Nuevo saldo)
     */
    cuentaCorriente(int numero, double cantidad){
        numeroCuenta = numero;
        saldo = cantidad;
    }

    /**
     * Método modificador del atributo "saldo"
     * Incrementa el saldo disponible
     * @param cantidad (Nueva cantidad)
     */
    public void deposito(double cantidad){
        saldo += cantidad;
    }

    /**
     * Método modificador del atributo "saldo"
     * Decrementa el saldo disponible
     * @param cantidad (Nueva cantidad)
     */
    public void reintegro(double cantidad){
        saldo -= cantidad;
    }

    /**
     * Selector de función, dada una operación y cantidad de dinero.
     * Al finalizar cualquier operación, devuelve el nuevo saldo.
     * @param op (Operación a elegir)
     * @param cantidad (Cantidad a añadir o reintegrar)
     * @return saldo (Saldo de la cuenta bancaria)
     */
    public double funcion(int op, double cantidad){
        if(op==0){
            deposito(cantidad);
        }else{
            reintegro(cantidad);
        }
        return saldo;
    }
}
