public class concursoLambda{
    public static int n = 0;

    public static void main(String[] args) throws Exception{
        Runnable r1 = () -> {
            for(int i = 0; i < 1000000; i++){
                n++;
            }
        };
        Runnable r2 = () -> {
            for(int i = 0; i < 1000000; i++){
                n--;
            }
        };
        Thread t1 = new Thread(r1); Thread t2 = new Thread(r2);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Resultado: " + n);
    }
}
