package tcpclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String numJugador;
    private Boolean gano = false;
    private Boolean elJuegoInicio= false;
    private String server = "127.0.0.1";

    public Client() {
        try {
            socket = new Socket(server, 7623);
            System.out.println("Se inicio el cliente: " + socket.getInetAddress() + ":" + socket.getPort());
            startStream();
        } catch (Exception ex) {
            System.out.println("Eror en el cliente: " + ex.getMessage());
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
            System.out.println("Enviando al servidor el mensaje: " + mensaje);
            out.writeUTF(mensaje);
            out.flush();
        } catch (IOException ex) {
            System.out.println("Error al enviar el mensaje: " + ex.getMessage());
        }

    }

    public void readData() {
        try {

            String info = in.readUTF();
            System.out.println("Mensaje recibido: " + info);

            if (info.equals("0")) {
                System.out.println("No acertó...");
            }

            if (info.equals("1")) {
                gano = true;
                System.out.println("Jugador gano...");
            }

            if (info.equals("2")) {
                System.out.println("Esperando otros jugadores");
            }
            
             if (info.equals("3")) {
                System.out.println("Ya inicio el juego");
                elJuegoInicio= true;
            }

            if (info.length() > 1) {

                System.out.println("Mensaje recibido del servidor: " + info);
                String resultado = info.substring(0, 2);
                if (resultado.equals("Ok")) {//Se reciben los dos primeros caracteres, jugadores.
                    numJugador = info.charAt(3) + ""; //Se recibe el caracter que esta en la posicion 4.
                    System.out.println("Soy el jugador numero: " + numJugador );
                }
            } 
            
        } catch (Exception ex) {
            System.out.println("Error al leer el mensaje: " + ex.getMessage());
            try {
                sleep(2000);
            } catch (InterruptedException ex1) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public int generarRandom() {
        Random random = new Random();
        int randomNum = random.nextInt(100);
        return randomNum;
    }

    public void generarMovimiento() {

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
            System.out.println("Error en la ejecución del cliente " + ex.getMessage());
        }

        if (gano) {
            System.out.println("Felicidades gano...");
        }

    }

    public void jugar() {

        if (numJugador != null) {//Si es diferente a null empieza a correr.
            while (!gano) {
                generarMovimiento();
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    System.out.println("Error en el metodo jugar...");
                }
                readData();
            }
        }
        
    }
}