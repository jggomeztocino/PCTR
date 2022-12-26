import java.util.*;
import java.lang.*;

public class intDefinidaMonteCarlo {
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    static double f(double x, int op){
        switch (op){
            case 2:
                return x;
            case 1:
                return Math.sin(x);
            default:
                return x;
        }
    }

    public static void main (String [] args){
        int contador_exitos = 0, op = 0, n = 0;
        double coordenada_x, coordenada_y;
        Random r = new Random();

        clearScreen();

        while(n<1){
            clearScreen();
            System.out.print("Introduzca el número de iteraciones deseadas: ");
            Scanner s = new Scanner(System.in);
            n = s.nextInt();
        }

        while(op!=1 && op!=2){
            clearScreen();
            System.out.println("Introduzca la función deseada: ");
            System.out.println("1. f(x) = sin");
            System.out.println("2. f(x) = x");
            Scanner s = new Scanner(System.in);
            op = s.nextInt();
        }

        clearScreen();

        for (int i=0; i<=n; i++){
            coordenada_x = r.nextDouble();
            coordenada_y = r.nextDouble();
            if(coordenada_y <= f(coordenada_x,op)){
                contador_exitos++;
            }
        }

        double resultado = (double)contador_exitos/n;
        System.out.print("Integral aproximada: ");
        System.out.println(resultado);
    }
}
