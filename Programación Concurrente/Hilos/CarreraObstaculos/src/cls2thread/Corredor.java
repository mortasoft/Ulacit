package cls2thread;

public class Corredor implements Runnable {//Clase Runner Implementa la Interfaz Runnable

    private final int id;//ID del corredor
    private int idEquipo;//ID del equipo
    private final int tiempoAvance = 1000;
    private final int linea;
    private int posicion;
    private boolean estaCorriendo;

    public Corredor(int id, int idEquipo, int linea) {
        this.id = id;
        this.idEquipo = idEquipo;
        this.linea = linea;
        this.posicion = 0; // Inician desde el principio de la linea
        estaCorriendo = true;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public void run() { //que tengo que hacer cuando me llaman

        System.out.println("El corredor " + id + " del equipo " + idEquipo + " ha iniciado a correr");

        while (Utils.running && estaCorriendo) {

            int tiempoRetraso = tiempoAvance + Utils.pista[linea][posicion];

            try {
                Thread.sleep(tiempoRetraso);

                // Si la posicion es menor al length de la pista sigue avanzando, sino es que ya llego
                if (posicion < Utils.pista[0].length - 1) {
                    this.setPosicion(posicion + 1);
                    System.out.println("El corredor " + id + " del equipo " + idEquipo + " esta en la posicion " + posicion);
                } else {
                    // Si es el ultimo corredor en llegar termina la carrera. Sino corre el siguiente
                    if (id == Utils.cantidadCorredores) {
                        Utils.equipoGanador = this.getIdEquipo();
                        Utils.running = false;
                        Utils.service.shutdown();//Se muere el Hilo
                    } else {
                        Corredor siguienteCorredor = new Corredor(id + 1, idEquipo, linea);
                        Utils.service.submit(siguienteCorredor);
                    }

                    estaCorriendo = false;

                }

            } catch (Exception ex) {
                System.out.println(ex);
                //System.err.println(ex);
            }

        }

    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

}
