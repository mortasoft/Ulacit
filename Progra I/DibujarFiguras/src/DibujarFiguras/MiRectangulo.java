package DibujarFiguras;

import java.awt.Color;
import java.awt.Graphics;

public class MiRectangulo {

    private int x1;         //coordenada x del primer punto final
    private int y1;         //coordenada y del primer punto final
    private int x2;         //coordenada x del segundo punto final
    private int y2;         //coordenada y del segundo punto final
    private Color color;    //el color de esta figura
    //constructor con valores de entrada

    public MiRectangulo(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
    //Dibuja el rectangulo en el color específico

    public void dibujar(Graphics g) {
        g.setColor(color);
        g.drawRect(x1, y1, x2, y2);
    }
}
