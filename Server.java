import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;

public class Server implements Runnable {

    private Socket socket;

    public static ArrayList<Account> accounts = new ArrayList<>();

    public static ArrayList<Course> courses = new ArrayList<>();

    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4242);
        while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new Server(socket));
            thread.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            // Read in string from client (DicussionBoard2)

            thread.join();

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
