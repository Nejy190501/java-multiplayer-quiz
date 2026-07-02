package server.net;

import java.net.*;
import java.io.*;
import shared.*;

class ClientHandler extends Thread {
    private final Socket socket;
    
    // Sample questions: Question, Option0, Option1, Option2, Option3, CorrectIndex
    private final String[][] questions = {
        {"What is the capital of France?", "Berlin", "Paris", "London", "Rome", "1"},
        {"What is 2 + 2?", "3", "4", "5", "6", "1"},
        {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", "1"},
        {"Who wrote Romeo and Juliet?", "Shakespeare", "Dickens", "Hemingway", "Austen", "0"}
    };
    
    ClientHandler(Socket socket) { this.socket = socket; }

    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream  in  = new ObjectInputStream(socket.getInputStream())) {

            for (int i = 0; i < questions.length; i++) {
                String[] q = questions[i];
                String payload = q[0] + "|" + q[1] + "|" + q[2] + "|" + q[3] + "|" + q[4];
                out.writeObject(new Response("QUESTION", payload));
                out.flush();

                // Wait for answer
                boolean validAnswerReceived = false;
                while (!validAnswerReceived) {
                    Request req = (Request) in.readObject();
                    if (req.getType().equals("ANSWER")) {
                        String answerIdx = req.getPayload();
                        if (answerIdx.equals(q[5])) {
                            out.writeObject(new Response("CORRECT", ""));
                        } else {
                            out.writeObject(new Response("WRONG", ""));
                        }
                        out.flush();
                        validAnswerReceived = true;
                    }
                }
            }
            
            out.writeObject(new Response("END", ""));
            out.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}