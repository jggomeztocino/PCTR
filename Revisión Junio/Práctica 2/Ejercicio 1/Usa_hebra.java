public class Usa_hebra {
    public static void main(String[] args) throws Exception{
        hebra p = new hebra(100000, 0);
        hebra q = new hebra(100000, 1);
        p.start();
        q.start();
        p.join();
        q.join();
        System.out.println(hebra.n);
    }
}
