package serpientes;

import javax.swing.JOptionPane;
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
public class Usuario implements Runnable {

    @Override
    public void run() {
        while (Utils.isRunning) {

            try {
                String opt = JOptionPane.showInputDialog(null, "Digite fila,columna");
                int fila = Integer.parseInt(opt.substring(0, 1));
                int columna = Integer.parseInt(opt.substring(2, 3));
                System.out.println("El usuario digito.... " + fila + "," + columna);
                int posicion = Utils.matriz[fila][columna];

                if (posicion == 1) {
                    JOptionPane.showMessageDialog(null, "El usuario se comio la semilla");
                    Utils.matriz[fila][columna] = 0;
                    Utils.semillasComidasUsuario++;
                    Thread.sleep(5000);
                }

                if (posicion == 2) {
                    JOptionPane.showMessageDialog(null, "NOOOOOOOOOOOOO!!! Muriooooooooo");
                    Utils.isRunning = false;
                    Utils.service.shutdown();
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Fallo");
                }

                Thread.sleep(5000);

            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }

        }
    }

}
