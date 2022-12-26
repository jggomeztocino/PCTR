/*Ejemplo de cliente de sockets
*@Antonio Tomeu
*@version 1.0
*/

/**
 * @author Jesús Gómez
 * @version 1.0
 */

import java.net.*;
import java.io.*;

/**
 * Clase que simula un cliente que manda peticiones a un servidor.
 * @author Jesús Gómez
 * @version 1.0
 */
public class clienteMultiple
{
    /**
     * Hilo principal donde se lanzan las peticiones al servidor
     * @param args No se usa
     */
    public static void main (String[] args)
    {
        int i = 0;
        int puerto = 2001;
        int nPeticiones = 1000;

        /**
         * Se lanzarán nPeticiones peticiones.
         */

        for(int j = 0; j < nPeticiones; j++){
            i = (int)(Math.random()*10);
            try {
                System.out.println("Realizando conexion...");
                Socket cable = new Socket("localhost", puerto);
                System.out.println("Realizada conexion a " + cable);
                PrintWriter salida = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        cable.getOutputStream())));
                salida.println(i);
                salida.flush();
                System.out.println("Cerrando conexion...");
                cable.close();

            }//try
            catch (Exception e) {
                System.out.println("Error en sockets...");
            }
        }
    }//main
}//clienteMultiple

