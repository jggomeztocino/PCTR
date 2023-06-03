public class usaprodCon extends Thread{
    private boolean productor;
    private int idHebra;
    private static prodCon monitor = new prodCon(10);
    public usaprodCon(boolean esProductor, int id){
        productor = esProductor;
        idHebra = id;
        this.start();
    }

    public void run(){
        if(productor) monitor.producir(idHebra);
        else monitor.consumir();
    }

    public static void main(String[] args) {
        Thread productor1 = new usaprodCon(true, 1);
        Thread productor2 = new usaprodCon(true, 2);
        Thread productor3 = new usaprodCon(true, 3);
        Thread consumidor1 = new usaprodCon(false, 1);
        Thread consumidor2 = new usaprodCon(false, 2);
        Thread consumidor3 = new usaprodCon(false, 3);
    }
}
