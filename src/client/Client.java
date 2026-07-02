package client;

import java.net.*;
import java.io.*;
import shared.*;

public class Client {
    public static void main(String[] args) throws Exception {
        try (Socket s = new Socket("localhost", 5555);
             ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
             ObjectInputStream  in  = new ObjectInputStream(s.getInputStream())) {

            out.writeObject(new Request("PING", ""));
            Response res = (Response) in.readObject();
            System.out.println(res.getStatus() + " " + res.getPayload());
        }
    }
}