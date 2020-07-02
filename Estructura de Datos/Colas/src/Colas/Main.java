package Colas;

public class Main {

    public static void main(String[] args) {
        Cola c = new Cola();
        c.encola(new Nodo(new Comida(1500, 4, "papas", "comida rapida")));
        c.encola(new Nodo(new Comida(1000, 1, "Flan", "postre")));
        c.encola(new Nodo(new Comida(8500, 3, "Arroz con pollo", "comida rapida")));
        c.encola(new Nodo(new Comida(500, 1, "combo 2", "comida rapida")));
        c.encola(new Nodo(new Comida(5000, 1, "combo 3", "comida rapida")));
        c.encola(new Nodo(new Comida(9000, 1, "combo 4", "comida rapida")));

        c.imprime();
        System.out.println("________________________________________");
        c.ordenar();
        c.imprime();

    }

}
