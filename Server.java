import java.io.*;
import java.net.*;
import javax.swing.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4242);
        waitingToConnect();
        Socket socket = serverSocket.accept();
        connected();
    }
    public static void waitingToConnect() {
        JOptionPane.showMessageDialog(null, "Client has not Connected",
                "SERVERIO", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void connected() {
        JOptionPane.showMessageDialog(null, "Client Connected",
                "SERVERIO", JOptionPane.INFORMATION_MESSAGE);
    }
}
