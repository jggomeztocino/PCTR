import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**                         sBonoLoto
 * Clase servidor de BonoLoto que implementa la interfaz iBonoLoto
 * @author Jesús Gómez
 * @version 1.0
 */

public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto{
    /**
     * Generador de números aleatorios
     */
    Random seed = new Random();

    /**
     * Array que almacena la combinación ganadora
     */
    private int[] combinacionGanadora = new int[6];

    /**
     * Método que reinicia el servidor y genera una nueva combinación ganadora
     * @throws RemoteException
     */
    public void resetServidor() throws RemoteException {
        // Generación de la combinación ganadora
        for(int i=0;i<6;i++){
            combinacionGanadora[i] = (seed.nextInt(48) + 1);
        }

        // Se muestra por pantalla la combinación ganadora
        System.out.println("Numero premiado:");
        for(int i=0;i<6;i++){
            System.out.print(combinacionGanadora[i] + " ");
        }
        System.out.println();
    }

    /**
     * Método que compara una apuesta con la combinación ganadora
     * @param apuesta Array con la apuesta realizada
     * @return true si la apuesta coincide con la combinación ganadora, false en caso contrario
     * @throws RemoteException
     */
    public boolean compApuesta(int[] apuesta)throws RemoteException{
        for(int i = 0; i<6; i++){
            if(apuesta[i]!=combinacionGanadora[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Constructor de la clase sBonoLoto que inicializa el servidor
     * @throws RemoteException
     */
    public sBonoLoto() throws RemoteException {
        resetServidor();
    }

    /**
     * Método main que inicializa el servidor
     * @param args Argumentos de la línea de comando
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        iBonoLoto servidor = new sBonoLoto();
        Registry registro = LocateRegistry.createRegistry(1099);
        Naming.bind("BonoLoto", servidor);
        System.out.println("Servidor preparado");
    }
}
