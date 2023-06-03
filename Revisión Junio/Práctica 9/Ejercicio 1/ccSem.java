import java.util.concurrent.Semaphore;

public class ccSem {
    private int numeroCuenta;
    private double saldo;
    private Semaphore semaforo = new Semaphore(1);

    public ccSem(int numeroCuenta, double saldo){
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    void deposito(double saldo){
        try{
            semaforo.acquire();
            this.saldo += saldo;
            semaforo.release();
        }catch(InterruptedException ignore){}
    }

    void reintegro(double saldo){
        try{
            semaforo.acquire();
            this.saldo -= saldo;
            semaforo.release();
        }catch(InterruptedException ignore){}
    }

    public double getSaldo() throws InterruptedException{
        try{
            semaforo.acquire();
            return saldo;
        }finally {
            semaforo.release();
        }
    }
}
