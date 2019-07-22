package tcpserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerClientTCP implements Runnable {

    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Matriz matriz;

    public ServerClientTCP(final Socket socket, Matriz matriz) {
        this.socket = socket;
        this.matriz= matriz;
        startStream();
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

    private void startStream() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Se iniciaron las variables de comunicacion");
        } catch (IOException ex) {
           System.out.println("Error al leer mensaje: " + ex.getMessage());
        }
    }

    public void readData() throws IOException {
        try {
            String info = in.readUTF();
            System.out.println("Mensaje recibido: " + info);
            if (info.equals("Start")) {
                if (Server.jugadoresConectados < 2) {
                    Server.jugadoresConectados++;
                    sendData("Ok." + Server.jugadoresConectados); //Esto le enseña al usuario cuando se logro conectar para jugar.
                    System.out.println("Se conectó un nuevo jugador. Jugadores totales: " +Server.jugadoresConectados);
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
                        System.out.println("Jugador " +jugador + " ganó");
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

    //Para enviarle un mensaje al Servidor 
    public void sendData(String mensaje) {
        try {
            System.out.println("Enviando al cliente el mensaje: " + mensaje);
            out.writeUTF(mensaje);
            out.flush();
        } catch (Exception ex) {
            System.out.println("Error al enviar mensaje: " + ex.getMessage());
        }

    }

}