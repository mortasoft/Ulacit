package tcpclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String server;
    private int puerto = 7623;
    private VentanaChat ventana;
    public boolean conectado = true;

    public Client(String serverIP, VentanaChat ventana) {
        try {
            this.server = serverIP;
            this.ventana = ventana;
            socket = new Socket(server, puerto);
            log("Se inicio el cliente: " + socket.getInetAddress() + ":" + socket.getPort());
            conectado = true;
            startStream();
        } catch (Exception ex) {
            log("[Client] No se puede conectar al servidor: " + ex.getMessage());
            conectado = false;
        }
    }

    private void startStream() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            log("[startStream] Error en el cliente: " + ex.getMessage());
        }
    }

    public void sendData(String mensaje) {
        try {
            log("[Cliente]: " + mensaje);
            out.writeUTF(mensaje);
            out.flush();
        } catch (IOException ex) {
            log("[sendData] Error al enviar el mensaje: " + ex.getMessage());
        }

    }

    public void readData() {
        try {

            if (conectado) {
                String info = in.readUTF();
                log("[Servidor]: " + info);
            }

        } catch (Exception ex) {
            log("[readData] Error al leer el mensaje: " + ex.getMessage());
            conectado = false;
        }
    }

    public String log(String txt) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fecha = formatoFecha.format(new Date());

        String salida = "[" + fecha + "] " + txt + "\n";

        if (this.ventana != null) {
            this.ventana.txtLogs.append(salida);
        }

        System.out.println(salida);
        return salida;

    }

    @Override
    public void run() {
        try {
            if (conectado) {
                log("Conectandose al servidor para enviar el mensaje de inicio...");
                sendData("Start");
            }

            while (conectado) {
                readData();
            }

        } catch (Exception ex) {
            log("Error en la ejecución del cliente " + ex.getMessage());
        }

    }

}
