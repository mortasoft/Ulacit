package Colas;

public class Comida {

    private int precio, cantidad;
    private String nombre, tipo;

    public Comida(int precio, int cantidad, String nombre, String tipo) {
        this.precio = precio;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\n" + "Comida{" + "precio=" + precio + ", cantidad=" + cantidad + ", nombre=" + nombre + ", tipo=" + tipo + '}';
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
