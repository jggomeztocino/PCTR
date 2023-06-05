import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
    int nPuntos = 0;
    int nAciertos  = 0;
    float aproximacionPi = 0f;
    public void reset() throws RemoteException {
        nPuntos = 0;
        nAciertos = 0;
        aproximacionPi = 0f;
    }

    public sPiMonteCarlo() throws RemoteException {
        reset();
    }

    public void masPuntos(int nPuntos) throws RemoteException {
        this.nPuntos += nPuntos;

        Random seed = new Random();
        float x, y;
        for(int i = 0; i < nPuntos; i++){
            x = seed.nextFloat();
            y = seed.nextFloat();
            if(x*x + y*y <= 1){
                nAciertos++;
            }
        }
    }

    public double aproxActual() throws RemoteException {
        if(nPuntos > 0){
            aproximacionPi = 4f * nAciertos / nPuntos;
        }
        return aproximacionPi;
    }

    public static void main(String[] args) throws Exception{
        iPiMonteCarlo stub = new sPiMonteCarlo();
        Registry puerto = LocateRegistry.createRegistry(1099);
        Naming.bind("PiMonteCarlo", stub);
        System.out.println("Servidor iniciado.");
    }
}
