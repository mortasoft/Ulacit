package DibujarFiguras;

import javax.swing.JFrame;

public class Taller1a {

    public static void main(String[] args) {
        //Rectangulo
        PanelDibujoMiRectangulo dibujo1 = new PanelDibujoMiRectangulo();
        JFrame ventana1 = new JFrame();
        ventana1.add(dibujo1);
        ventana1.setSize(400, 400);
        ventana1.setVisible(true);
        //Ovalo
        PanelDibujoMiOvalo dibujo2 = new PanelDibujoMiOvalo();
        JFrame ventana2 = new JFrame();
        ventana2.add(dibujo2);
        ventana2.setSize(400, 400);
        ventana2.setVisible(true);
    }
}
