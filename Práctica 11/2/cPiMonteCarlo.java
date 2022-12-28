import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Clase principal del programa cPiMonteCarlo, que permite a un usuario consultar y modificar
 * la aproximación del valor de pi mediante el algoritmo de Monte Carlo.
 */
public class cPiMonteCarlo{
    /**
     * Menú mostrado al usuario para seleccionar una opción.
     */
    private static final String MENU = "Opciones:\n" +
            "0. Reiniciar servidor\n" +
            "1. Consultar aproximación actual\n" +
            "2. Incluir más puntos a la aproximación\n" +
            "Pulse cualquier otra tecla para salir del programa.\n";


    /**
     * Método principal que ejecuta el programa.
     * @param arg argumentos pasados por línea de comandos (no se utilizan en este programa).
     * @throws Exception si ocurre algún problema al ejecutar el programa.
     */
    public static void main(String []arg) throws Exception{
        // Obtiene el registro del servidor RMI y busca el objeto remoto del servidor de Monte Carlo.
        Registry registro = LocateRegistry.getRegistry(1099);
        iPiMonteCarlo servidor = (iPiMonteCarlo) Naming.lookup("//localhost/Servidor");

        // Variable que almacena la opción seleccionada por el usuario.
        int opcion;

        // Bucle principal del programa, que se repite hasta que el usuario seleccione la opción de salir.
        do{
            // Muestra el menú y lee la opción seleccionada por el usuario.
            System.out.println(MENU);
            opcion = new Scanner(System.in).nextInt();

            // Realiza una acción en función de la opción seleccionada por el usuario.
            switch(opcion){
                case 0:
                    // Reinicia el servidor.
                    servidor.reset();
                    System.out.println("Servidor reiniciado");
                    break;
                case 1:
                    // Consulta la aproximación actual del servidor.
                    System.out.println("Aproximación actual: " + servidor.aproxActual());
                    break;
                case 2:
                    // Pide al usuario el número de puntos a incluir en la aproximación y los envia al servidor.
                    System.out.println("Número de puntos a incluir en la aproximación:");
                    int puntos = new Scanner(System.in).nextInt();
                    servidor.masPuntos(puntos);
                    break;
                default:
                    // Si se selecciona cualquier otra opción, se sale del programa.
                    opcion = -1;
                    break;
            }
        } while(opcion != -1);
    }
}

