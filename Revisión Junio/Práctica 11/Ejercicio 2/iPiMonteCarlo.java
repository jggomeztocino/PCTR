import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iPiMonteCarlo extends Remote {
    public void reset() throws RemoteException;
    public void masPuntos(int nPuntos)  throws RemoteException;
    public double aproxActual() throws RemoteException;
}
