package Logica;

import Ventanas.Tablero;
import java.io.ObjectOutputStream;
import tcpclient.Client;

public class ClienteDamas {

    public static String numJugador;
    public static boolean gameStart=false;

    public static void interpretarMensaje(String msg, ObjectOutputStream out) {

        if (msg.equals("Ok.1")) {
            numJugador = "1";
            Ventanas.MenuJugar.mensaje = "Usted es el jugador 1. Esperando otros jugadores";
        }

        if (msg.equals("Ok.2")) {
            numJugador = "2";
            Ventanas.MenuJugar.mensaje = "Usted es el jugador 2. Iniciando el juego";
            Tablero ventanaTablero = new Tablero();
            ventanaTablero.setVisible(true);
        }

        if (msg.equals("Se alcanzo el limite de jugadores conectados")) {
            Ventanas.MenuJugar.mensaje = "Se alcanzo el limite de jugadores conectados";
        }

        if (msg.equals("Esperando otros jugadores")) {
            System.out.println(Client.generarFecha() + "Esperando otros jugadores...");
        }

        if (msg.equals("No")) {
            System.out.println("No ha iniciado el juego...");
        }

        if (msg.equals("Si")) {
            System.out.println("Ya empezo el juego");
        }
    }
}
