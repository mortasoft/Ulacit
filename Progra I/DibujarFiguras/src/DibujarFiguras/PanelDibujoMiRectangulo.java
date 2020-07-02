package DibujarFiguras;

//Programa que utiliza la clase MiRectangulo
//para dibujar rectangulos al azar.
import java.awt.Color;
import java.awt.Graphics;
import java.security.SecureRandom;
import javax.swing.JPanel;

public class PanelDibujoMiRectangulo extends JPanel {

    private SecureRandom numerosAleatorios = new SecureRandom();
    private MiRectangulo[] rectangulo; //arreglo de rectangulos
//constructor, crea un panel con figuras al azar

    public PanelDibujoMiRectangulo() {
        setBackground(Color.BLACK);
        rectangulo = new MiRectangulo[5 + numerosAleatorios.nextInt(5)];
//crea rectangulos
        for (int cuenta = 0; cuenta < rectangulo.length; cuenta++) {
//genera coordenadas aleatorias
            int x1 = numerosAleatorios.nextInt(200);
            int y1 = numerosAleatorios.nextInt(200);
            int x2 = numerosAleatorios.nextInt(200);
            int y2 = numerosAleatorios.nextInt(200);
//genera un color aleatorio
            Color color = new Color(numerosAleatorios.nextInt(100), numerosAleatorios.nextInt(100), numerosAleatorios.nextInt(100));
//agrega el rectangulo a la lista de rectangulos a mostrar en pantalla
            rectangulo[cuenta] = new MiRectangulo(x1, y1, x2, y2, color);
        }
    }
//para cada arreglo de figuras, dibuja las figuras individuales

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//dibuja los rectangulos
        for (MiRectangulo rectangulo : rectangulo) {
            rectangulo.dibujar(g);
        }
    }
}
