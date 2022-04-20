import java.io.*;
import java.net.*;
import javax.swing.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4242);
        waitingToConnect();
        Socket socket = serverSocket.accept();
        connected();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String x = br.readLine();
        br.close();
        if (x.equals("n")) {
            socket.close();
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
}
