package tcpserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerClient implements Runnable {

    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerClient(final Socket socket) {
        this.socket = socket;
        startStream();
    }

    @Override
    public void run() {
        while (true) {
            try {
                readData();
            } catch (Exception ex) {
                System.out.println(Server.generarFecha() + "El cliente se desconectó: " + ex.getMessage());
                Logica.jugadoresConectados--;
                break;
            }
        }
    }

    private void startStream() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(Server.generarFecha() + "Se iniciaron las variables de comunicación");
        } catch (IOException ex) {
            System.out.println(Server.generarFecha() + "Error al leer mensaje startstream: " + ex.getMessage());
        }
    }

    public void readData() throws IOException {
        try {
            // Se recibe el mensaje del cliente
            String info = in.readUTF();
            System.out.println(Server.generarFecha() + "Mensaje recibido: " + info);
            Logica.interpretarMensaje(info, out);

        } catch (Exception ex) {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(Server.generarFecha() + "Readdata: Error al leer mensaje: " + ex.getMessage());
        }
    }

    //Para enviarle un mensaje al Cliente
    public void sendData(String mensaje) {
        try {
            System.out.println(Server.generarFecha() + "Enviando al cliente el mensaje: " + mensaje);
            out.writeUTF(mensaje);
            out.flush();
        } catch (Exception ex) {
            System.out.println(Server.generarFecha() + "Error al enviar mensaje: " + ex.getMessage());
        }

    }

}
