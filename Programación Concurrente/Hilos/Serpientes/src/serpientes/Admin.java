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
public class Admin {

    /**
     *
     * @param cantidadSerpientes
     * @param tamano
     */
    public void iniciar(int cantidadSerpientes, int tamano) {

        inicializarMatriz(tamano);

        System.out.println("Generando serpientes");
        for (int i = 0; i < cantidadSerpientes; i++) {
            Serpiente serpiente = new Serpiente(i);
            Utils.service.submit(serpiente);
        }

        System.out.println("Generando el plantador de Semillas");
        PlantadorSemillas plantador = new PlantadorSemillas();
        Utils.service.submit(plantador);

        System.out.println("Generando el usuario");
        Usuario user = new Usuario();
        Utils.service.submit(user);

    }

    /**
     *
     * @param tamano
     */
    public void inicializarMatriz(int tamano) {

        Utils.tamano = tamano;
        Utils.matriz = new int[Utils.tamano][Utils.tamano];
        Utils.imprimirMatriz();
        System.out.println("Se inicializo la matriz");

    }

}
