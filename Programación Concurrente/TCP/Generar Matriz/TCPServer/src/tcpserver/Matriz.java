package tcpserver;

import java.util.Random;

public class Matriz {
    
    public int gridCien[][] = new int[100][100];

    public Matriz() { 
        llenarGrid();
    }

    public static int generarRandom() {
        Random random = new Random();
        int randomNum = random.nextInt(100);
        return randomNum;
    }

    public void llenarGrid() {

        for (int contadorSustituir = 0; contadorSustituir < 5; contadorSustituir++) {
            int fila = generarRandom();
            int columna = generarRandom();
            gridCien[fila][columna] = 1;
        }

        imprimirMatriz();
        
        System.out.println("Matriz generada correctamente");

    }

    public void imprimirMatriz() {

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.printf("%1d ", gridCien[i][j]);
            }
            System.out.println();

        }
    }
    
    public boolean verificar(int fila, int columna){
        
        if(gridCien[fila][columna] == 1){
            return true;
        }else{
            return false;
        }
        
    }
        
}
