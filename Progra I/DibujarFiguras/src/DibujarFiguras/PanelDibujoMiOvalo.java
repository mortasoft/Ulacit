package DibujarFiguras;

//Programa que utiliza la clase MiOvalo
//para dibujar ovalos al azar.
import java.awt.Color;
import java.awt.Graphics;
import java.security.SecureRandom;
import javax.swing.JPanel;

public class PanelDibujoMiOvalo extends JPanel {

    private SecureRandom numerosAleatorios = new SecureRandom();
    private MiOvalo[] ovalo; //arreglo de ovalos
    //constructor, crea un panel con figuras al azar

    public PanelDibujoMiOvalo() {
        setBackground(Color.BLACK);
        ovalo = new MiOvalo[5 + numerosAleatorios.nextInt(5)];
        //crea ovalos
        for (int cuenta = 0; cuenta < ovalo.length; cuenta++) {
            //genera coordenadas aleatorias
            int x1 = numerosAleatorios.nextInt(200);
            int y1 = numerosAleatorios.nextInt(200);
            int x2 = numerosAleatorios.nextInt(200);
            int y2 = numerosAleatorios.nextInt(200);
            //genera un color aleatorio
            Color color = new Color(numerosAleatorios.nextInt(100), numerosAleatorios.nextInt(100), numerosAleatorios.nextInt(100));
            //agrega el ovalo a la lista de ovalos a mostrar en pantalla
            ovalo[cuenta] = new MiOvalo(x1, y1, x2, y2, color);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MiOvalo ovalo : ovalo) {
            ovalo.dibujar(g);
        }
    }
}
