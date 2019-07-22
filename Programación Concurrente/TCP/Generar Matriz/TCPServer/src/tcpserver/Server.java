
package tcpserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private Socket socket;
    private ServerSocket serverSocket;
    private ExecutorService service;
    private int puerto= 7623;
    private Matriz matriz;
    public static int jugadoresConectados=0;

    public Server() {
        try {
            service = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(7623);
            System.out.println("Iniciando servidor. En el Puerto: "+puerto);
            System.out.println("Generando matriz aleatoria...");
            matriz = new Matriz();
            
            startConnection();
        } catch (Exception ex) {
            System.out.println("Error al iniciar el servidor: " + ex.getMessage());
        }
    }

    private void startConnection() {
        try {
            while (true) {
                System.out.println("Esperando mensaje...");
                socket = serverSocket.accept();
                System.out.println("Conexion entrante de la dirección: " + socket.getInetAddress());
                service.submit(new ServerClient(socket,matriz));
            }
        } catch (Exception ex) {
            System.out.println("Error al iniciar la conexión: " + ex.getMessage());
        }
    }

}
