import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**                                 cBonoLoto
 * Clase cliente para BonoLoto.
 * Permite a un usuario introducir su apuesta y comprobar si ha sido premiado.
 *
 * @author Jesús Gómez
 * @version 1.0
 */
public class cBonoLoto {

    /**
     * Método principal para ejecutar el cliente del juego BonoLoto.
     *
     * @param args Argumentos de la línea de comandos. No se utilizan en este caso.
     * @throws Exception Si ocurre algún error durante la ejecución del programa.
     */
    public static void main(String[] args) throws Exception {
        // Crea un Scanner para leer los números de la entrada para la apuesta del usuario
        Scanner input = new Scanner(System.in);

        // Crea un array para almacenar la apuesta del usuario
        int[] apuesta = new int[6];

        // Pide al usuario que introduzca los números de su apuesta
        System.out.println("Introduzca los numeros de su apuesta:");

        // Lee los números de la apuesta del usuario y los almacena en el array
        for (int i = 0; i < 6; i++) {
            apuesta[i] = input.nextInt();
        }

        // Obtiene una referencia al registro del servidor RMI
        Registry registro = LocateRegistry.getRegistry(1099);

        // Obtiene una referencia al servidor BonoLoto a través del registro
        iBonoLoto servidor = (iBonoLoto) Naming.lookup("//localhost/BonoLoto");

        // Llama al método compApuesta del servidor para comprobar si la apuesta del usuario ha sido premiada
        boolean premiado = servidor.compApuesta(apuesta);

        // Muestra un mensaje al usuario indicando si ha sido premiado o no
        if (premiado) {
            System.out.println("¡Enhorabuena! Has acertado :)");
        } else {
            System.out.println("No acertaste, más suerte la próxima vez :(");
        }
    }
}
