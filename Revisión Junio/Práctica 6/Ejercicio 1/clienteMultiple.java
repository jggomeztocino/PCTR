/*Ejemplo de cliente de sockets
 *@Antonio Tomeu
 *@version 1.0
 */


import java.net.*;
import java.io.*;

public class clienteMultiple
{
    public static void main (String[] args)
    {
        int puerto = 2001;
        for (int nPeticiones = 0; nPeticiones < 100; nPeticiones++) {
            try {
                Socket conexion = new Socket("localhost", puerto);
                System.out.println("Cliente " + (nPeticiones+1) + ": Conexión establecida con "+ conexion);
                PrintWriter respuesta = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(conexion.getOutputStream())));
                respuesta.println((int)(Math.random()*10));
                respuesta.flush();
                conexion.close();
            } catch (Exception e) {

            }
        }
    }//main
}//Cliente_Hilos

