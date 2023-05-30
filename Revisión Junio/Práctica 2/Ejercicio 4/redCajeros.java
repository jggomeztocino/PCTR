public class redCajeros {
    public static void main(String[] args) throws Exception{
        cuentaCorriente cuenta = new cuentaCorriente(1,0);
        cajero c1 = new cajero(0,cuenta,33);
        cajero c2 = new cajero(1,cuenta,33);
        Thread t1;
        Thread t2;
        for (int i = 0; i<10000; i++){
            t1 = new Thread(c1);
            t2 = new Thread(c2);
            t1.start();
            t2.start();
        }
        System.out.println(cuenta.getSaldo());
    }
}
