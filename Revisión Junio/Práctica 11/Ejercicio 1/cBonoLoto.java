import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class cBonoLoto {
    public static void main(String[] args) throws Exception{
        int [] apuesta = new int[6];
        System.out.println("Introduzca su apuesta: ");
        for (int i = 0; i < 6; i++) {
            apuesta[i] = Integer.parseInt(System.console().readLine());
        }
        Registry puerto = LocateRegistry.getRegistry(1099);
        iBonoLoto stub = (iBonoLoto) Naming.lookup("//localhost/BonoLoto");
        if(stub.compApuesta(apuesta)) System.out.println("¡¡¡ENHORABUENA!!! ¡¡Ha ganado la BonoLoto!!");
        else System.out.println("Resultado no premiado. ¡Suerte la próxima vez!");
    }
}
//[29, 4, 40, 35, 29, 4]
