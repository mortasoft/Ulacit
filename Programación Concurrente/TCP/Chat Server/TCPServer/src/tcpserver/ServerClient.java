package tcpserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JTextArea;

public class ServerClient implements Runnable {

    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Server server;
    private boolean conectado;
    private JTextArea txtLogs = new JTextArea();

    public ServerClient(final Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        conectado = true;
        startStream();
    }

    public JTextArea getTxtLogs() {
        return txtLogs;
    }

    public void setTxtLogs(JTextArea txtLogs) {
        this.txtLogs = txtLogs;
    }

    @Override
    public void run() {
        while (conectado) {
            try {
                readData();
            } catch (Exception ex) {
                server.log("Error con el cliente: " + ex.getMessage());
                break;
            }
        }
    }

    private void startStream() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            server.log("Se iniciaron las variables de comunicacion");
        } catch (IOException ex) {
            server.log("Error al leer mensaje: " + ex.getMessage());
        }
    }

    public void readData() throws IOException {
        try {
            String info = in.readUTF();
            server.log("Mensaje recibido: " + info);
            txtLogs.append(server.log(info));
            if (info.equalsIgnoreCase("Start")) {
                txtLogs.append(server.log("[Server] Bienvenido al chat de soporte, en que le podemos ayudar..."));
                sendData("Bienvenido al chat de soporte, en que le podemos ayudar...");
            }

        } catch (Exception ex) {
            server.log("[readData] El cliente esta desconectado. "+ ex.getMessage());
            conectado = false;
        }
    }

    //Para enviarle un mensaje al Servidor 
    public void sendData(String mensaje) {
        try {
            out.writeUTF(mensaje);
            out.flush();
            server.log("Enviando al cliente el mensaje: " + mensaje);
        } catch (Exception ex) {
            server.log("[sendData] El cliente esta desconectado");
        }

    }

}
