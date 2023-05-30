public class cuentaCorriente {
    private int numeroCuenta;
    private double saldo;

    public cuentaCorriente(int numeroCuenta, double saldo){
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    void deposito(double saldo){
        this.saldo += saldo;
    }

    void reintegro(double saldo){
        this.saldo -= saldo;
    }

    public double getSaldo(){
        return saldo;
    }
}
