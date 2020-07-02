package cls2thread;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin {

    int cantidadEquipos;
    int jugadoresPorEquipo;

    public Admin(int cantidadLineas, int cantidadPosiciones, int jugadoresPorEquipo) {
        Utils.pista = new int[cantidadLineas][cantidadPosiciones];
        this.cantidadEquipos = cantidadLineas;
        this.jugadoresPorEquipo = jugadoresPorEquipo;
        System.out.println("Generando pista...");
        generarPista();

    }

    private void generarPista() {
        for (int i = 0; i < Utils.pista.length; i++) {
            for (int j = 0; j < Utils.pista[i].length; j++) {
                Utils.pista[i][j] = 0;
            }
        }
    }

    public void runApp() {
        // Crea un corredor nuevo para cada uno de los equipos y lo pone a correr
        for (int i = 0; i < cantidadEquipos; i++) {
            Corredor corredorNuevo = new Corredor(i + 1, i + 1, i);
            Utils.service.submit(corredorNuevo);
        }

        while (Utils.running) {
            try {
                // Imprime los resultados de la carrera
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("El ganador de la carrera fue el equipo: " + Utils.equipoGanador);

    }
}
