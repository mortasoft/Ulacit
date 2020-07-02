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

/**
 *
 * @author Mortasoft
 */
public class PlantadorSemillas implements Runnable {

    /**
     *
     * @return
     */
    public int posicionAleatoria() {
        int numMaximo = Utils.tamano;
        int salida = (int) ((Math.random()) * numMaximo);
        return salida;
    }

    @Override
    public void run() {

        while (Utils.isRunning) {

            try {
                for (int i = 0; i < 3; i++) {
                    plantarSemilla();
                }
                Utils.imprimirMatriz();
                Thread.sleep(10000);
            } catch (Exception ex) {
                System.out.println("Error:" + ex);
            }
        }

    }

    /**
     *
     */
    public void plantarSemilla() {
        int fila = posicionAleatoria();
        int columna = posicionAleatoria();
        Utils.matriz[fila][columna] = 1;
        Utils.semillasGeneradasTotal++;
        System.out.println("Se planto una semilla en la posicion: " + "(" + fila + "," + columna + ")");
    }

}
