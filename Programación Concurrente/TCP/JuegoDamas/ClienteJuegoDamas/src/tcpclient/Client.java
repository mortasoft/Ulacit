package tcpclient;

import Logica.ClienteDamas;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String numJugador;
    private String server;
    private boolean isUp = true;
    private boolean gameStart = false;

    public Client(String server) {
        try {
            this.server = server;
            socket = new Socket(server, 7623);
            System.out.println(generarFecha() + "Se inicio el cliente: " + socket.getInetAddress() + ":" + socket.getPort());
            startStream();
        } catch (Exception ex) {
            Ventanas.MenuJugar.mensaje = "No se puede conectar al servidor. Revise la IP";
            System.out.println(generarFecha() + " No se puede conectar al servidor. Revise la IP: " + ex.getMessage());
            isUp = false;
        }
    }

    private void startStream() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendData(String mensaje) {
        try {
            System.out.println(generarFecha() + "Enviando al servidor el mensaje: " + mensaje);
            out.writeUTF(mensaje);
            out.flush();
        } catch (IOException ex) {
            System.out.println(generarFecha() + "Error al enviar el mensaje: " + ex.getMessage());
        }

    }

    public static String generarFecha() {
        return "[" + new Date().toString() + "] ";
    }

    public void readData() {
        try {

            String info = in.readUTF();
            System.out.println("Mensaje recibido: " + info);
            ClienteDamas.interpretarMensaje(info, out);

        } catch (Exception ex) {
            System.out.println("Error al leer el mensaje: " + ex.getMessage());
            try {
                sleep(2000);
            } catch (InterruptedException ex1) {
                System.out.println(ex1.getMessage());
            }
        }
    }

    @Override
    public void run() {
        try {
            if (isUp) {
                System.out.println(generarFecha() + "Conectandose al servidor para enviar el mensaje de inicio...");
                sendData("Start");
                readData();

                try {

                    if (!gameStart) {
                        System.out.println("Enviando ya inició");
                        sendData("yaInicio?");
                        readData();
                        Thread.sleep(1000);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        } catch (Exception ex) {
            Ventanas.MenuJugar.mensaje = "No se puede conectar al servidor";
            System.out.println(generarFecha() + "No se puede conectar al servidor. Verifique la IP");
        }

    }

}
