/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulacit
 */
public class ServerClientUDP implements Runnable {

    private DatagramPacket in, out;
    private DatagramSocket socket;
    private Matriz matriz;

    public ServerClientUDP(Matriz matriz) {
        try {
            socket = new DatagramSocket(7471);
            this.matriz = matriz;
        } catch (SocketException ex) {
            Logger.getLogger(ServerClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                readData();
            } catch (Exception ex) {
                System.out.println("Error con el cliente: " + ex.getMessage());
                break;
            }
        }
    }

    public void sendData(String mensaje) {

        try {
            System.out.println("Enviando al cliente el mensaje: " + mensaje);
            out = new DatagramPacket(mensaje.getBytes(), 0, mensaje.getBytes().length, in.getAddress(), in.getPort());
            socket.send(out);
        } catch (Exception ex) {
            System.out.println("Error al enviar mensaje: " + ex.getMessage());
        }

    }

    public void readData() {
        try {
            byte[] buffer = new byte[400];
            in = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(in);
            System.out.println(in.getAddress());
            //String info = new String(in.getData()); Solo asi no sirve
            String info = new String(in.getData(),0,in.getLength());
            
            System.out.println("Mensaje recibido: " + info);
            if (info.equals("Start")) {
                if (Server.jugadoresConectados < 2) {
                    Server.jugadoresConectados++;
                    sendData("Ok." + Server.jugadoresConectados); //Esto le enseña al usuario cuando se logro conectar para jugar.
                    System.out.println("Se conectó un nuevo jugador. Jugadores totales: " + Server.jugadoresConectados);
                } else {
                    sendData("Se alcanzo el limite de jugadores conectados. ");
                }
            } else {
                // Si son dos jugadores conectados inicia el juego, sino se espera...
                if (Server.jugadoresConectados == 2) {
                    String temporal[] = info.split(","); //En el arreglo se ponen los mensajes de fila y columna.
                    int fila = Integer.parseInt(temporal[0]);
                    int columna = Integer.parseInt(temporal[1]);
                    int jugador = Integer.parseInt(temporal[2]);
                    boolean resultado = verificar(fila, columna);
                    if (resultado) {
                        sendData("1"); // Si pego
                        System.out.println("Jugador " + jugador + " ganó");
                    } else {
                        sendData("0"); // No pego
                    }
                } else {
                    sendData("2");  // Esperando otros jugadores
                }
            }

        } catch (Exception ex) {
            System.out.println("Error al leer mensaje: " + ex.getMessage());
        }
    }

    public boolean verificar(int fila, int columna) {
        return matriz.verificar(fila, columna);
    }

}
