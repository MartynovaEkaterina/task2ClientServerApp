import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int port = 5346;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Сервер запущен!");
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                out.println("Привет! Как тебя зовут?");
                final String userName = in.readLine();
                out.println("Ты ребенок? (да/нет)");
                inputLine = in.readLine();
                if ("да".equals(inputLine)) {
                    out.println(String.format("Добро пожаловать в детскую зону, %s! Любишь ли ты играть в активные игры? (да/нет)", userName));
                    inputLine = in.readLine();
                    if ("да".equals(inputLine)) {
                        out.println("Мы ждем тебя в нашем игровом центре Веселый слоник. Приходи! Будет весело!");
                    } else {
                        out.println("Мы ждем тебя в нашем книжном клубе Белый парус. Приходи! У нас есть много интересных историй со всего света!");
                    }
                } else {
                    out.println(String.format("Добро пожаловать во взрослую зону, %s! Любишь ли ты активный отдых? (да/нет)", userName));
                    inputLine = in.readLine();
                    if ("да".equals(inputLine)) {
                        out.println("Мы ждем тебя в нашем спорт клубе Эверест. Приходи! У нас есть скалодром!");
                    } else {
                        out.println("Мы ждем тебя в нашем клубе Дом кино. Приходи! У нас сейчас проходит фестиваль французских фильмов!");
                    }
                }
            }
        } finally {
            System.out.println("Сервер закрыт!");
            clientSocket.close();
            out.close();
            in.close();
            serverSocket.close();
        }
    }
}
