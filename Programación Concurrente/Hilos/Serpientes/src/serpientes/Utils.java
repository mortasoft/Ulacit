package serpientes;
//MIT License
//
//Copyright (c) 2020 Mortasoft
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Mortasoft
 */
public class Utils {

    /**
     *
     */
    public static int tamano;

    /**
     *
     */
    public static int matriz[][];

    /**
     *
     */
    public static ExecutorService service = Executors.newCachedThreadPool();

    /**
     *
     */
    public static int semillasGeneradasTotal = 0;

    /**
     *
     */
    public static int semillasComidasSerpientes = 0;

    /**
     *
     */
    public static int semillasComidasUsuario = 0;

    /**
     *
     */
    public static boolean isRunning = true;

    /**
     *
     */
    public static void imprimirMatriz() {

        // FILAS
        for (int i = 0; i < Utils.matriz.length; i++) {

            // COLUMNAS
            for (int j = 0; j < Utils.matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");

        System.out.println("Semillas comidas por serpientes: " + semillasComidasSerpientes);
        System.out.println("Semillas generadas en total: " + semillasGeneradasTotal);
        System.out.println("Semillas comidas por usuario: " + semillasComidasUsuario);

    }

}
