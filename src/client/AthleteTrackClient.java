package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AthleteTrackClient {

    // тест през терминала за няколко клиента
    // cd bin
    //java client.AthleteTrackClient

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 8080;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(in.readLine());

            String userInput;
            while ((userInput = console.readLine()) != null) {
                out.println(userInput);
                String serverResponse = in.readLine();
                if (serverResponse == null) {
                    System.out.println("Server disconnected.");
                    break;
                }
                System.out.println(serverResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}