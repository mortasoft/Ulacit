package tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    private Socket socket;
    private ServerSocket serverSocket;
    private ExecutorService service;
    private int puerto = 7623;
    private VentanaChat ventana;
    public static ArrayList<ServerClient> clientes;

    // Se usa para la conexion sin GUI
    public Server() throws IOException {
        try {
            service = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(puerto);
            log("Iniciando servidor en el Puerto: " + puerto);
            clientes = new ArrayList<>();
            iniciarServer();
        } catch (Exception ex) {
            log("Error al iniciar el servidor: " + ex.getMessage());
            serverSocket.close();
        }
    }

    public ArrayList<ServerClient> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<ServerClient> clientes) {
        this.clientes = clientes;
    }

    @Override
    public void run() {
        iniciarServer();
    }

    // Constructor para el uso en GUI
    public Server(VentanaChat ventana) throws IOException {
        try {
            this.ventana = ventana;
            clientes = new ArrayList<>();
        } catch (Exception ex) {
            log("Error al iniciar el servidor: " + ex.getMessage());
        }
    }

    private void iniciarServer() {
        try {
            // Almacena las conexiones
            service = Executors.newCachedThreadPool();

            // Crea el socket en el puerto 
            serverSocket = new ServerSocket(puerto);
            log("Iniciando servidor. En el Puerto: " + puerto);

            while (true) {
                log("Esperando mensaje...");
                socket = serverSocket.accept();
                log("Conexion entrante de la dirección: " + socket.getInetAddress());
                ServerClient cliente = new ServerClient(socket, this);
                clientes.add(cliente);
                ventana.crearTab(clientes.indexOf(cliente));
                service.submit(cliente);
            }
        } catch (Exception ex) {
            log("Error al iniciar la conexión: " + ex.getMessage());
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

    public static String log2(String txt) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fecha = formatoFecha.format(new Date());

        String salida = "[" + fecha + "] " + txt + "\n";

        System.out.println(salida);
        return salida;

    }
}
