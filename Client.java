import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client {
    private static String host;
    private static int port;
    public static void showWelcomeMessage() {
        JOptionPane.showMessageDialog(null, "Welcome",
                "Client", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showHostMessage() {
        host = JOptionPane.showInputDialog(null, "What is the hostname?",
                "Client", JOptionPane.QUESTION_MESSAGE);
    }

    public static void showPortMessage() {
        Integer portTemp;
        portTemp = Integer.parseInt(JOptionPane.showInputDialog(null, "What is the port number?",
                "Client", JOptionPane.QUESTION_MESSAGE));
        port = portTemp;
    }
    public static void error() {
        Integer portTemp;
        portTemp = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid port or host",
                "Client", JOptionPane.ERROR_MESSAGE));
        port = portTemp;
    }
    public static void allGood() {
        JOptionPane.showMessageDialog(null, "Connection Successful",
                "Client", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        while (true) {
            showWelcomeMessage();
            showHostMessage();
            showPortMessage();
            try {
                Socket socket = new Socket(host, port);
                break;
            }
            catch (Exception e) {
                error();
            }
        }
        allGood();

    }
}
