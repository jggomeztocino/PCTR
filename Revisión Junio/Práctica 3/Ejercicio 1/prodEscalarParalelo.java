public class prodEscalarParalelo extends Thread{
    private int id;
    private int inicio;
    private int fin;
    private static int [] v1 = new int[1000000];
    private static int [] v2 = new int[1000000];
    private static int nHbebras = 10;
    public static int[] productoParcial = new int[nHbebras];
    public prodEscalarParalelo(int idHebra, int inicio, int fin){
        this.id = idHebra;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run(){
        productoParcial[id] = 0;
        for(int i = inicio; i < fin; i++){
            v1[i] = i; v2[i] = i;
            productoParcial[id] += v1[i] * v2[i];
        }
    }

    public static void main(String[] args) throws Exception{
        int tamIndividual = 1000000 / nHbebras;
        int resto = 1000000 % nHbebras;
        Thread t1 = new prodEscalarParalelo(0, 0, tamIndividual);
        Thread t2 = new prodEscalarParalelo(1, tamIndividual,tamIndividual*2);
        Thread t3 = new prodEscalarParalelo(2,tamIndividual*2,tamIndividual*3);
        Thread t4 = new prodEscalarParalelo(3,tamIndividual*3,tamIndividual*4);
        Thread t5 = new prodEscalarParalelo(4,tamIndividual*4,tamIndividual*5);
        Thread t6 = new prodEscalarParalelo(5,tamIndividual*5,tamIndividual*6);
        Thread t7 = new prodEscalarParalelo(6,tamIndividual*6,tamIndividual*7);
        Thread t8 = new prodEscalarParalelo(7,tamIndividual*7,tamIndividual*8);
        Thread t9 = new prodEscalarParalelo(8,tamIndividual*8,tamIndividual*9);
        Thread t10 = new prodEscalarParalelo(9,tamIndividual*9,(tamIndividual*10)+resto);
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start(); t6.start(); t7.start(); t8.start(); t9.start(); t10.start();
        t1.join(); t2.join(); t3.join(); t4.join(); t5.join(); t6.join(); t7.join(); t8.join(); t9.join(); t10.join();
        int resultado = 0;
        for (int i = 0; i < nHbebras; i++){
            resultado += productoParcial[i];
        }
        System.out.println("Resultado: " + resultado);
    }

}
