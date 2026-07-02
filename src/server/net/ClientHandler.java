package server.net;

import java.net.*;
import java.io.*;
import shared.*;

class ClientHandler extends Thread {
    private final Socket socket;
    ClientHandler(Socket socket) { this.socket = socket; }

    public void run() {
        try (ObjectInputStream  in  = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            Request req = (Request) in.readObject();
            Response res = switch (req.getType()) {
                case "PING" -> new Response("OK", "PONG");
                default     -> new Response("ERR", "unknown");
            };
            out.writeObject(res);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();   // später Logger
        }
    }
}