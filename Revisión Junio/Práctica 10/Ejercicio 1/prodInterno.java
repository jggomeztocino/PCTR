import mpi.*;

import java.util.Arrays;
import java.util.Random;

// -------------------------------- COMPILACIÓN --------------------------------
// PowerShell (Windows): javac -cp $env:MPJ_HOME/lib/mpj.jar prodInterno.java
// CMD (Windows): javac -cp .;%MPJ_HOME%/lib/mpj.jar prodInterno.java
// Unix (Linux y MacOS): javac -cp .:$MPJ_HOME/lib/mpj.jar prodInterno.java
// ---------------------------------- EJECUCIÓN --------------------------------
// Windows: mpjrun.bat -np 2 prodInterno
// Unix (Linux y MacOS): mpjrun.sh -np 2 prodInterno
// -----------------------------------------------------------------------------
public class prodInterno {
    public static void main(String[] args) {
        MPI.Init(args);
        int id = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int master = 0;
        int n = 10;
        int [] buffer1 = new int[n];
        int [] buffer2 = new int[n];
        int [] productoInterno = new int[n];

        if(id==0){ // ID Maestro
            for(int i = 0; i < n; i++){
                buffer1[i] = buffer2[i] = i;
            }
            System.out.println("Enviando vectores originales desde la hebra master" + id);
            MPI.COMM_WORLD.Send(buffer1,0,n,MPI.INT,1,100);
            MPI.COMM_WORLD.Send(buffer2,0,n,MPI.INT,1,101);
            MPI.COMM_WORLD.Recv(productoInterno,0,n,MPI.INT,1,102);
            System.out.println("Vector recibido en la hebra master " + id + ". Imprimiendo...");
            System.out.println(Arrays.toString(productoInterno));
        }else{
            MPI.COMM_WORLD.Recv(buffer1,0,n,MPI.INT,0,100);
            MPI.COMM_WORLD.Recv(buffer2,0,n,MPI.INT,0,101);
            System.out.println("Vectores recibidos. Realizando calculos desde la hebra slave" + id);
            for(int i = 0; i < n; i++){
                productoInterno[i] = buffer1[i] * buffer2[i];
            }
            System.out.println("Enviando vector resultado desde la hebra slave " + id);
            MPI.COMM_WORLD.Send(productoInterno,0,n,MPI.INT,0,102);
        }
        MPI.Finalize();
    }
}
