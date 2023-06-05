import mpi.MPI;

import java.util.Arrays;

public class escalMultiple {
    // -------------------------------- COMPILACIÓN --------------------------------
    // PowerShell (Windows): javac -cp $env:MPJ_HOME/lib/mpj.jar escalMultiple.java
    // CMD (Windows): javac -cp .;%MPJ_HOME%/lib/mpj.jar escalMultiple.java
    // Unix (Linux y MacOS): javac -cp .:$MPJ_HOME/lib/mpj.jar escalMultiple.java
    // ---------------------------------- EJECUCIÓN --------------------------------
    // Windows: mpjrun.bat -np 10 escalMultiple
    // Unix (Linux y MacOS): mpjrun.sh -np 10 escalMultiple
    // -----------------------------------------------------------------------------

    public static void main(String[] args) {
        MPI.Init(args);
        int id = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int master = 0;
        int n = 10;
        int[] buffer = new int[n];
        if (id == 0) { // ID Maestro
            for (int i = 0; i < n; i++) {
                buffer[i] = i;
            }
            System.out.println("Vector original en la hebra master " + id + ": " + Arrays.toString(buffer));
        }
        MPI.COMM_WORLD.Bcast(buffer, 0, n, MPI.INT, 0);
        if(id != 0){
            //System.out.println("Vector recibido en la hebra slave " + id + ". Escalando...");
            for(int i = 0; i < n; i++){
                buffer[i] = buffer[i] * id;
            }
            System.out.println("Vector escalado en la hebra slave " + id + ": " + Arrays.toString(buffer));
        }
        MPI.Finalize();
    }
}
