import java.util.Random;
import java.util.Scanner;

public class NewtonRaphson {

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static double f(double x, int op){
        switch (op){
            case 1:
                return Math.cos(x) - Math.pow(x,3);
            case 2:
                return Math.pow(x,2) - 5;
            default:
                return x;
        }
    }

    static double fprima(double x, int op){
        switch (op){
            case 1:
                return -Math.sin(x)-3*Math.pow(x,2);
            case 2:
                return 2*x;
            default:
                return x;
        }
    }

    public static void main (String [] args){
        int iteraciones = 0, op = 0;
        double x0 = -1, xN = 0, xN1 = 0;
        Scanner s;
        Random r = new Random();

        clearScreen();

        while(x0<0 && iteraciones<1){
            System.out.print("Introduzca x0: ");
            s = new Scanner(System.in);
            x0 = s.nextDouble();
            clearScreen();
            System.out.print("Introduzca el número de iteraciones deseadas: ");
            s = new Scanner(System.in);
            iteraciones = s.nextInt();
            clearScreen();
        }

        xN=x0;

        while(op!=1 && op!=2){
            clearScreen();
            System.out.println("Introduzca la función deseada: ");
            System.out.println("1. f(x) = cos(x) - x^3 en [0,1]");
            System.out.println("2. f(x) = x^2 - 5 en [2,3]");
            s = new Scanner(System.in);
            op = s.nextInt();
            clearScreen();
        }

        for (int i=0; i<iteraciones; i++){
            if (fprima(xN,op) != 0){
                xN1 = (double)(xN - f(xN,op) / fprima(xN,op));
                System.out.print("Iteracion: ");
                System.out.print(i+1);
                System.out.print(". Aproximacion: ");
                System.out.println(xN1);
                xN=xN1;
            }
        }

        System.out.println("");
        System.out.print("Resultado: ");
        System.out.println(xN);

    }
}
