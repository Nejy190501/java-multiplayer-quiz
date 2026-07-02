package server.net;

import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        try (ServerSocket ss = new ServerSocket(5555)) {
            System.out.println("Server läuft auf Port 5555");
            while (true) new ClientHandler(ss.accept()).start();
        }
    }
}