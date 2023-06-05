import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class cPiMonteCarlo {
    public static void main(String[] args) throws Exception{
        Registry puerto = LocateRegistry.getRegistry(1099);
        iPiMonteCarlo stub = (iPiMonteCarlo) Naming.lookup("//localhost/PiMonteCarlo");
        System.out.println("Conexión establecida con el servidor.");
        while(true){
            System.out.println("Opciones:");
            System.out.println("1. Añadir puntos.");
            System.out.println("2. Obtener aproximación actual.");
            System.out.println("3. Reiniciar aproximación actual.");
            System.out.println("0. Salir.");
            int op = Integer.parseInt(System.console().readLine());
            switch (op){
                case 1:
                    System.out.printf("Introduzca el número de puntos a añadir: ");
                    int n = Integer.parseInt(System.console().readLine());
                    while(n < 1){
                        n = Integer.parseInt(System.console().readLine());
                    }
                    stub.masPuntos(n);
                    System.out.println("Puntos añadidos.");
                    break;
                case 2:
                    System.out.println("Aproximación actual: " + stub.aproxActual());
                    break;
                case 3:
                    stub.reset();
                    System.out.println("Aproximación y datos relativos reiniciados.");
                    break;
            }
        }
    }
}
