package client;

import java.net.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import shared.*;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private QuizAppUI ui;
    private int score = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client client = new Client();
            client.start();
        });
    }

    public void start() {
        ui = new QuizAppUI(new AnswerListener());
        ui.setVisible(true);

        new Thread(() -> {
            try {
                socket = new Socket("localhost", 5555);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                // Start listening for server responses
                while (true) {
                    Response res = (Response) in.readObject();
                    handleResponse(res);
                }
            } catch (Exception e) {
                System.err.println("Connection error: " + e.getMessage());
                ui.updateQuestion("Connection to server failed.", new String[0]);
            }
        }).start();
    }

    private void handleResponse(Response res) {
        String status = res.getStatus();
        String payload = res.getPayload();

        if (status.equals("QUESTION")) {
            // Payload format: "QuestionText|Option1|Option2|Option3|Option4"
            String[] parts = payload.split("\\|");
            if (parts.length >= 5) {
                String question = parts[0];
                String[] options = {parts[1], parts[2], parts[3], parts[4]};
                ui.updateQuestion(question, options);
            }
        } else if (status.equals("CORRECT")) {
            score += 10;
            ui.updateScore(score);
        } else if (status.equals("WRONG")) {
            // Optionally handle wrong answer UI feedback
        } else if (status.equals("END")) {
            ui.updateQuestion("Quiz Finished! Final Score: " + score, new String[0]);
            ui.disableButtons();
        }
    }

    private class AnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            try {
                if (out != null) {
                    out.writeObject(new Request("ANSWER", command));
                    out.flush();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}