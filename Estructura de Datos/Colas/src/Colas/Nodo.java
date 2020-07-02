package Colas;

public class Nodo {

    private Comida dato;
    private Nodo atras;

    public Nodo(Comida dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return "" + dato + '}';
    }

    public Comida getDato() {
        return dato;
    }

    public void setDato(Comida dato) {
        this.dato = dato;
    }

    public Nodo getAtras() {
        return atras;
    }

    public void setAtras(Nodo atras) {
        this.atras = atras;
    }

}
