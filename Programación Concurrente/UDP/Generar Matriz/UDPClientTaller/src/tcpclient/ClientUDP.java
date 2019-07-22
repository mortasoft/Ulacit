package tcpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientUDP extends Thread {

    private DatagramPacket in, out;
    private DatagramSocket socket;
    private String numJugador;
    private Boolean gano = false;
    private Boolean elJuegoInicio = false;

    public ClientUDP() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendData(String mensaje) {
        try {

            System.out.println("Enviando al servidor el mensaje: " + mensaje);

            out = new DatagramPacket(mensaje.getBytes(), 0, mensaje.getBytes().length, InetAddress.getLocalHost(), 7471);
            socket.send(out);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readData() {
        try {
            byte[] buffer = new byte[400];
            in = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(in);
            String info = new String(in.getData());

            System.out.println("Mensaje recibido: " + info);

            if (info.equals("0")) {
                System.out.println("No acertó...");
            }

            if (info.equals("1")) {
                gano = true;
                System.out.println("Jugador gano...");
            }

            if (info.equals("2")) {
                System.out.println("Esperando otros jugadores.");
            }

            if (info.equals("3")) {
                System.out.println("Ya inicio el juego.");
                elJuegoInicio = true;
            }

            if (info.length() > 1) {
                System.out.println("Mensaje recibido del servidor: " + info);
                String resultado = info.substring(0, 2);
                if (resultado.equals("Ok")) {//Se reciben los dos primeros caracteres, jugadores.
                    numJugador = info.charAt(3) + ""; //Se recibe el caracter que esta en la posicion 4.
                    System.out.println("Soy el jugador numero: " + numJugador);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int generarRandom() {
        Random random = new Random();
        int randomNum = random.nextInt(100);
        return randomNum;
    }

    public void generarMovimiento() { //Saca la fila, columna y lo manda al servidor. 
        int fila = generarRandom();
        int columna = generarRandom();
        String mensaje = fila + "," + columna + "," + numJugador;
        sendData(mensaje);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Conectandose al servidor para enviar el mensaje de inicio...");
            sendData("Start");
            readData();

        } catch (Exception ex) {
            System.out.println("Error en la ejecución del cliente: " + ex.getMessage());
        }

        if (gano) {
            System.out.println("Felicidades gano...");
        }

    }

    public void jugar() {//Esto siempre esta corriendo. 
        if (numJugador != null) {//Si es diferente a null empieza a correr.
            while (!gano) {
                generarMovimiento();
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
                readData();
            }
        }
    }

}
