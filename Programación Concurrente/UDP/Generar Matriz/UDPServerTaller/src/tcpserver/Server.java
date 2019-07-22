
package tcpserver;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket;
    private ServerSocket serverSocket;
    private int puerto= 7623;
    private Matriz matriz;
    public static int jugadoresConectados=0;

    public Server() {
        try {
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
            ServerClientUDP cliente = new ServerClientUDP(matriz);
            while (true) {
                cliente.run();
            }
        } catch (Exception ex) {
            System.out.println("Error al iniciar la conexión: " + ex.getMessage());
        }
    }

}
