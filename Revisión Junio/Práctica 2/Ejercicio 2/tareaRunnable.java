public class tareaRunnable implements Runnable{
    private int tipoHilo;
    private int nVueltas;
    private static int n = 0;

    public tareaRunnable(int nVueltas, int tipoHilo){
        this.nVueltas=nVueltas;
        this.tipoHilo=tipoHilo;
    }
    @Override
    public void run(){
        switch (tipoHilo) {
            case 0 -> {
                for (int i = 0; i < nVueltas; i++) {
                    n++;
                }
            }
            case 1 -> {
                for (int i = 0; i < nVueltas; i++) {
                    n--;
                }
            }
        }
    }

    public static int getN(){
        return n;
    }
}
