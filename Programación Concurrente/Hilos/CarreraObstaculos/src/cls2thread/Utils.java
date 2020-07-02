package cls2thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Utils {

    public static int cantidadCorredores = 6;
    public static int equipoGanador;

    public static ExecutorService service = Executors.newCachedThreadPool();

    public static int[][] pista;//Cada linea de la carrera representada por una lista de 10 posiciones

    public static boolean running = true;//Bandera de aviso (cuando un Hilo esta activo)

    public static void imprimirPista() {

        for (int i = 0; i < Utils.pista.length; i++) {
            for (int j = 0; j < Utils.pista[i].length; j++) {
                System.out.print(pista[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");

    }
}
