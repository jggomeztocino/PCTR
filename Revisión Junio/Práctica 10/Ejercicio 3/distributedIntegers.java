import mpi.MPI;

// ----------------------------------- COMPILACIÓN -----------------------------------
// PowerShell (Windows): javac -cp $env:MPJ_HOME/lib/mpj.jar distributedIntegers.java
// CMD (Windows): javac -cp .;%MPJ_HOME%/lib/mpj.jar distributedIntegers.java
// Unix (Linux y MacOS): javac -cp .:$MPJ_HOME/lib/mpj.jar distributedIntegers.java
// ------------------------------------- EJECUCIÓN -----------------------------------
// Windows: mpjrun.bat -np 10 distributedIntegers
// Unix (Linux y MacOS): mpjrun.sh -np 10 distributedIntegers
// -----------------------------------------------------------------------------------
public class distributedIntegers {
    public static int primosEnRango(int inicio, int fin) {
        int primos = 0;
        for (int i = inicio+1; i <= fin; i++) {
            if (esPrimo(i)) {
                primos++;
            }
        }
        return primos;
    }

    private static boolean esPrimo(int i) {
        if(i == 1) return false;
        for (int j = 2; j < (i/2)+1; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int n = 10000000;
        int primos = 0;
        int[] tam = new int[1];
        int[] resultadoParcial = new int[1];
        int[] resultado = new int[1];

        if (rank == 0) {
            tam[0] = n / size;
        }

        MPI.COMM_WORLD.Bcast(tam, 0, 1, MPI.INT, 0);

        if (rank == size - 1) {
            resultadoParcial[0] = primosEnRango(rank * tam[0], n);
        } else {
            resultadoParcial[0] = primosEnRango(rank * tam[0], (rank + 1) * tam[0]);
        }

        MPI.COMM_WORLD.Reduce(resultadoParcial, 0, resultado, 0, 1, MPI.INT, MPI.SUM, 0);

        if (rank == 0) {
            System.out.println("Numero de numeros primos encontrados: " + resultado[0]);
        }
    }
}
