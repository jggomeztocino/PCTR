/*Ejemplo de servidor de sockets multihilo
 *@Antonio Tomeu
 *@version 1.0
 */


import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class ServidorHiloconPool implements Runnable {
    Socket enchufe;
    /**
     * Constructor parametrizado
     * @param s socket que utilizará el servidor
     */
    public ServidorHiloconPool(Socket s)
    {enchufe = s;}

    /**
     * Implementación del método Run. Procesa una petición realizada al servidor
     */
    public void run()
    {
        try{
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();
            for(j=1; j<=100; j++){
                System.out.println("El hilo "+Thread.currentThread().getName()+" escribiendo el dato "+i);
            }
            enchufe.close();
            System.out.println("El hilo "+Thread.currentThread().getName()+" cierra su conexion...");
        } catch(Exception e) {System.out.println("Error...");}
    }//run

    /**
     * Método main, crea un pool de hilos, pone en marcha un servidor y crea objetos de la clase {@code ServidorHiloConPool} para que procesen las peticiones.
     * @param args
     */
    public static void main (String[] args)
    {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        int puerto = 2001;
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);

            while (true){
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                pool.execute(new ServidorHiloconPool(cable));
            }//while
        } catch (Exception e)
        {System.out.println("Error en sockets...");}
    }//main

}//Servidor_Hilos

