import java.io.*;
import java.net.*;
import javax.swing.*;

public class Server implements Runnable {

    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4242);
        waitingToConnect();
        while (true) {
            Socket socket = serverSocket.accept();
            connected();
            new Thread(new Server(socket)).start();
        }

    }
    public static void waitingToConnect() {
        JOptionPane.showMessageDialog(null, "Client has not Connected",
                "SERVERIO", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void connected() {
        JOptionPane.showMessageDialog(null, "Client Connected",
                "SERVERIO", JOptionPane.INFORMATION_MESSAGE);
    }

    public void run() {

    }
}
