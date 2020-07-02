package Colas;

public class Cola {

    private Nodo frente, ultimo;

    public void encola(Nodo n) {//metodo para agregar en la fila.
        if (frente == null) {
            frente = n;
        } else {
            ultimo.setAtras(n);
        }
        ultimo = n;
    }

    public Nodo atiende() {
        Nodo aux = frente;
        if (frente != null) {
            frente = frente.getAtras();
            aux.setAtras(null);// es para separar los nodos.
            if (frente == null) {
                ultimo = null;
            }
        }

        return aux;

    }

    public String toString() {
        String msj = "";
        Nodo aux = frente;
        if (aux != null) {
            while (aux != null) {
                msj += aux;
                aux = aux.getAtras();// busca en todos el metodo
            }

        } else {
            msj = "No tiene datos";
        }

        return msj;
    }

    public boolean encuentra(int precio) {
        Nodo aux = frente;
        boolean existe = false;
        while (!existe && aux != null) {
            if (precio == aux.getDato().getPrecio()) {
                existe = true;
            }
            aux = aux.getAtras();
        }
        return existe;
    }

    public Nodo extraer(String nombre, String tipo) {

        Nodo aux = frente;
        while (aux != null) {
            Comida comida = (Comida) aux.getDato();
            if (nombre.equals(comida.getNombre()) && tipo.equals(comida.getTipo())) {
                return aux;
            }
            aux = aux.getAtras();
        }
        return null;
    }

    public void ordenar() {

        Nodo aux = frente;
        Cola colaux = new Cola();
        Nodo nodoColaux = null;
        Nodo anteriorColaux = null;
        Nodo aux2 = new Nodo(new Comida(aux.getDato().getPrecio(), aux.getDato().getCantidad(), aux.getDato().getNombre(), aux.getDato().getTipo()));
        if (frente != null) {// el primer nodo que encuentre lo pasa a la otra cola
            colaux.encola(aux2);
            aux = aux.getAtras();// esto es para que continue con el siguiente
            nodoColaux = colaux.frente;

            while (aux != null) {
                nodoColaux = colaux.frente;
                anteriorColaux = null;
                while (nodoColaux != null) {
                    if (aux.getDato().getPrecio() >= nodoColaux.getDato().getPrecio()) { // Si el nodo que estoy comparando es mayor
                        if (nodoColaux.getAtras() == null) {
                            colaux.encola(new Nodo(new Comida(aux.getDato().getPrecio(), aux.getDato().getCantidad(), aux.getDato().getNombre(), aux.getDato().getTipo())));//Este seria el nodo final, el que llevaria el precio más alto
                            break;
                        } else {
                            if (aux.getDato().getPrecio() <= nodoColaux.getAtras().getDato().getPrecio()) {
                                Nodo n = new Nodo(new Comida(aux.getDato().getPrecio(), aux.getDato().getCantidad(), aux.getDato().getNombre(), aux.getDato().getTipo()));
                                n.setAtras(nodoColaux.getAtras());
                                nodoColaux.setAtras(n);//Este seria el nodo final, el que llevaria el precio más alto);
                                break;
                            }
                        }

                    } else {// este es en caso de que deba de acomodar el nodo en el medio
                        if (anteriorColaux == null) {
                            colaux.frente = new Nodo(new Comida(aux.getDato().getPrecio(), aux.getDato().getCantidad(), aux.getDato().getNombre(), aux.getDato().getTipo()));;
                            colaux.frente.setAtras(nodoColaux);
                            break;
                        }

                    }
                    anteriorColaux = nodoColaux;
                    nodoColaux = nodoColaux.getAtras();
                }
                aux = aux.getAtras();
            }
            frente = colaux.frente;

        } else {
            return;
        }
    }

    public void imprime() {

        Nodo aux = frente;
        while (aux != null) {
            Comida comida = (Comida) aux.getDato();
            System.out.println(comida);
            aux = aux.getAtras();
        }

    }

}
