import java.util.concurrent.locks.ReentrantLock;

public class cCRL {
    private int numeroCuenta;
    private double saldo;

    private ReentrantLock cerrojo = new ReentrantLock();

    public cCRL(int numeroCuenta, double saldo){
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    void deposito(double saldo){
        cerrojo.lock();
        this.saldo += saldo;
        cerrojo.unlock();
    }

    void reintegro(double saldo){
        cerrojo.lock();
        this.saldo -= saldo;
        cerrojo.unlock();
    }

    public double getSaldo(){
        cerrojo.lock();
        try{
            return saldo;
        }finally {
            cerrojo.unlock();
        }
    }
}
