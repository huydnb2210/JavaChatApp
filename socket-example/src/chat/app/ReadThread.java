package chat.app;

import java.io.*;

import java.net.*;
public class ReadThread implements Runnable{
    private BufferedReader in;
    private Socket socket;
    private Client client;

    public ReadThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String response = "";
        do {
            try {
                response = in.readLine();
               System.out.println("\n" + response);
               if (client.getUserName() != null) {
                   System.out.print("[" + client.getUserName() + "]: ");
               }
           } catch (IOException e) {
               MyLogger.info(String.format("Error reading in this server: '%s'", e.getMessage()));
               e.printStackTrace();
           }
        }
        while (!response.equals("Exit"));
    }
}
