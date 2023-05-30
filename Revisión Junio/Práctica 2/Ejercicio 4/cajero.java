public class cajero implements Runnable{
    private int tipoCajero;
    private cuentaCorriente cuenta;
    private int importe;

    public cajero(int tipoCajero, cuentaCorriente cuenta, int importe){
        this.tipoCajero = tipoCajero;
        this.cuenta = cuenta;
        this.importe = importe;
    }
    public void run(){
        switch (tipoCajero) {
            case 1 -> cuenta.deposito(importe);
            case 2 -> cuenta.reintegro(importe);
        }
    }
}
