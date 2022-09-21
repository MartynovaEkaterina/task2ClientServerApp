import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static String host = "netology.homework";
    private static int port = 5346;
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static BufferedReader readerConsole;

    public static void main(String[] args) throws IOException {
        try {
            clientSocket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            readerConsole = new BufferedReader(new InputStreamReader(System.in));
            String respServer;
            String userText = "Привет!";
            while (userText != null) {
                out.println(userText);
                respServer = in.readLine();
                System.out.println(respServer);
                userText = readerConsole.readLine();
            }
        } finally {
            System.out.println("Клиент закрыт!");
            clientSocket.close();
            out.close();
            in.close();
        }
    }
}