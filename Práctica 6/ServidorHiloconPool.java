/*Ejemplo de servidor de sockets multihilo
 *@Antonio Tomeu
 *@version 1.0
*/


import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase que simula un servidor que recibe peticiones desde un cliente.
 * @author Jesús Gómez
 * @version 1.0
 */
public class ServidorHiloconPool
  implements Runnable
{
    /**
     * Socket que procesará las peticiones
     */
    Socket enchufe;
    /**
     * Número de hilo
     */
    int nThread;

    /**
     * Constructor de clase
     * @param s Socket asignado
     * @param i Número de hilo
     */
    public ServidorHiloconPool(Socket s, int i)
    {enchufe = s; nThread = i;}

    /**
     * Método heredado de Thread,
     * e implementado por Runnable.
     * @see Thread
     * @see Runnable
     * @see BufferedReader
     * @see InputStreamReader
     */
    public void run()
    {
    try{
        BufferedReader entrada = new BufferedReader(
                                    new InputStreamReader(
                                        enchufe.getInputStream()));
        String datos = entrada.readLine();
        int j;
        int i = Integer.parseInt(datos);
        for(j=1; j<=20; j++){
        System.out.println("El hilo " + Thread.currentThread().getName() + " escribiendo el dato "+i);
        Thread.sleep(1000);}
        enchufe.close();
        System.out.println("El hilo "+ Thread.currentThread().getName() +" cierra su conexion...");
    } catch(Exception e) {System.out.println("Error...");}
    }//run

    /**
     * Hilo principal, donde, a través de un Pool de hilos, se lanzarán y gestionarán
     * las distintas peticiones, además de asignar los Sockets.
     * @param args No se usa
     * @see ExecutorService
     * @see Executors
     * @see ServerSocket
     */
    public static void main (String[] args)
{
    int i = 0;
    int puerto = 2001;
    ExecutorService executor = Executors.newCachedThreadPool();
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);

            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                executor.execute(new ServidorHiloconPool(cable,i));
                i++;
            }
      } catch (Exception e)
        {System.out.println("Error en sockets...");}
}//main

}//ServidorHiloconPool

