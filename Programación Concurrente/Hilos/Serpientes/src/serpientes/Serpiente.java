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
public class Serpiente implements Runnable {

    private int id;
    private int fila;
    private int columna;
    private boolean comio;

    /**
     *
     * @param id
     */
    public Serpiente(int id) {
        this.id = id;
        this.comio = false;
        posicionAleatoria();
    }

    /**
     *
     * @param id
     * @param fila
     * @param columna
     */
    public Serpiente(int id, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.id = id;
        this.comio = false;
    }

    /**
     *
     */
    public void posicionAleatoria() {
        int numMaximo = Utils.tamano;
        int filaAleatoria = (int) ((Math.random()) * numMaximo);
        int columnaAleatoria = (int) ((Math.random()) * numMaximo);
        fila = filaAleatoria;
        columna = columnaAleatoria;

        if (Utils.matriz[fila][columna] == 1) {
            this.comio = true;
            Utils.semillasComidasSerpientes++;
            System.out.println("La serpiente " + id + " se comio una semilla en la posicion: " + "(" + fila + "," + columna + ")");
        } else {
            this.comio = false;
        }

        Utils.matriz[fila][columna] = 2;
        System.out.println("Se genero una serpiente en la posicion: (" + fila + "," + columna + ")");
    }

    /**
     *
     * @return
     */
    public int getFila() {
        return fila;
    }

    /**
     *
     * @param fila
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     *
     * @return
     */
    public int getColumna() {
        return columna;
    }

    /**
     *
     * @param columna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public void run() {

        while (Utils.isRunning) {

            try {
                Thread.sleep(10000);
                moverseYComer();
                if (comio) {
                    Thread.sleep(10000);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }

        }

    }

    /**
     *
     */
    public void moverseYComer() {
        Utils.matriz[fila][columna] = 0;
        posicionAleatoria();
    }

}
