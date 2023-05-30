import java.util.Random;
import java.util.Scanner;

public class intDefinidaMonteCarlo {
    static double f(double x, int op){
        if (op == 0) {
            return Math.sin(x);
        }
        return x;
    }
    static double monteCarlo(int n, int op){
        int contador_exitos = 0;
        double coordenada_x, coordenada_y;
        Random seed = new Random();
        for(int i = 0; i < n; i++){
            coordenada_x = seed.nextDouble();
            coordenada_y = seed.nextDouble();
            if (coordenada_y <= f(coordenada_x, op)){
                contador_exitos++;
            }
        }
        double aproximacion = ((double) contador_exitos /n);
        System.out.print("Integral aproximada: " + aproximacion);
        return aproximacion;
    }

    public static void main(String[] args) {
        System.out.print("Introduzca el numero de puntos a lanzar: ");
        Scanner input = new Scanner(System.in);
        int puntos = input.nextInt();
        System.out.println("Introduzca la función a analizar:");
        System.out.println("1. sin(x)");
        System.out.println("2. x");
        System.out.print("Opcion: ");
        input = new Scanner(System.in);
        monteCarlo(puntos,input.nextInt());
    }
}
