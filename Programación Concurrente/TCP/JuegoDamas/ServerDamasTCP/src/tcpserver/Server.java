package tcpserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private Socket socket;
    private ServerSocket serverSocket;
    private ExecutorService service;
    private int puerto = 7623;

    public Server() {
        try {
            service = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(7623);
            System.out.println(generarFecha() + "Iniciando servidor en el Puerto: " + puerto);
            startConnection();
        } catch (Exception ex) {
            System.out.println(generarFecha() + "Error al iniciar el servidor: " + ex.getMessage());
        }
    }

    private void startConnection() {
        try {
            while (true) {
                System.out.println(generarFecha() + "Esperando mensaje de los clientes...");
                socket = serverSocket.accept();
                System.out.println(generarFecha() + "Conexion entrante de la dirección: " + socket.getInetAddress());
                service.submit(new ServerClient(socket));
            }
        } catch (Exception ex) {
            System.out.println(generarFecha() + "Error al iniciar la conexión: " + ex.getMessage());
        }
    }

    public static String generarFecha() {
        return "[" + new Date().toString() + "] ";
    }

}
