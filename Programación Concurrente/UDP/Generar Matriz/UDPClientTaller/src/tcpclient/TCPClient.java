package tcpclient;

public class TCPClient {

    public static void main(String[] args) {
        //ClientTCP client = new ClientTCP();
        ClientUDP client = new ClientUDP();
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
