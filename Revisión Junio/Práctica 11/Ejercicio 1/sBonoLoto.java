import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Random;

public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto{
    private int [] combinacionGanadora;

    public sBonoLoto() throws RemoteException {
        resetServidor();
    }

    public void resetServidor()  throws RemoteException{
        Random seed = new Random();
        combinacionGanadora = new int[6];
        for (int i = 0; i < 6; i++) {
            combinacionGanadora[i] = seed.nextInt(49) + 1;
        }
        System.out.println("La combinación ganadora generada es: " + Arrays.toString(combinacionGanadora));
    }

    public boolean compApuesta(int[] apuesta) throws RemoteException{
        for (int i = 0; i < 6; i++) {
            if (apuesta[i] != combinacionGanadora[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando servidor...");
        iBonoLoto servidor = new sBonoLoto();
        Registry puerto = LocateRegistry.createRegistry(1099);
        Naming.bind("BonoLoto", servidor);
    }
}