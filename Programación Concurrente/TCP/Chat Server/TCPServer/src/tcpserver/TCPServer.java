package tcpserver;

public class TCPServer {

    public static void main(String[] args) {
        try {
            new Server();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}