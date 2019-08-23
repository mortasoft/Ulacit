package tcpserver;

import java.io.ObjectOutputStream;

public class Logica {

    public static int jugadoresConectados;

    public static void interpretarMensaje(String msg, ObjectOutputStream out) {

        if (msg.equals("Start")) {
            if (jugadoresConectados < 2) {
                jugadoresConectados++;
                sendData("Ok." + jugadoresConectados, out); //Esto le enseña al usuario cuando se logro conectar para jugar.
                System.out.println("Se conectó un nuevo jugador. Jugadores totales: " + jugadoresConectados);
            } else {
                sendData("Se alcanzo el limite de jugadores conectados", out);
            }
        }

        if (msg.equals("yaInicio?")) {
            if (jugadoresConectados == 2) {
                sendData("Si", out);
            } else {
                System.out.println("Enviando al cliente que no....");
                sendData("No", out);
            }
        } else {
            // Si son dos jugadores conectados inicia el juego, sino se espera...
            if (jugadoresConectados == 2) {
                String temporal[] = msg.split(","); //En el arreglo se ponen los mensajes de fila y columna.
                int jugador = Integer.parseInt(temporal[2]);
                if (true) {
                    sendData("1", out); // Si pego
                } else {
                    sendData("0", out); // No pego
                }
            } else {
                sendData("Esperando otros jugadores", out);  // Esperando otros jugadores
            }
        }

    }

    public static void sendData(String mensaje, ObjectOutputStream out) {
        try {
            System.out.println("Enviando al cliente el mensaje: " + mensaje);
            out.writeUTF(mensaje);
            out.flush();
        } catch (Exception ex) {
            System.out.println("Error al enviar mensaje: " + ex.getMessage());
        }

    }

}
