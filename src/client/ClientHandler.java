package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Welcome to Athlete Track App. Enter a command:");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                String response = handleCommands(inputLine);
                out.println(response);

            }

        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    private String handleCommands(String command) {
        switch (command.toLowerCase()) {
            case "gettime":
                return "Current time: " + java.time.LocalDateTime.now();
            case "hello":
                return "Hello, client!";
            case "exit":
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
                return "Connection closed.";
            default:
                return "Unknown command.";
        }
    }


}