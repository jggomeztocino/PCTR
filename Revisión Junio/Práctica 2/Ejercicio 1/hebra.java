public class hebra extends Thread{
    private int tipoHilo;
    public static int n = 0;
    private int nVueltas;

    public hebra(int nVueltas, int tipoHilo){
        this.nVueltas=nVueltas;
        this.tipoHilo=tipoHilo;
    }

    /**
     * Condición de concurso: Se da cuando dos o más tareas concurrentes acceden a recursos compartidos entre ellas al mismo tiempo
     * sin control de exclusión mutua.
     */
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
}
