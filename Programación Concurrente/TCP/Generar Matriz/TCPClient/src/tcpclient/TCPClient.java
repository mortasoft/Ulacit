package tcpclient;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
        while (true) {
            try {
                Thread.sleep(1000);
                client.jugar();
            } catch (InterruptedException ex) {
                System.out.println("Error al iniciar el cliente");
            }
        }

    }

}
