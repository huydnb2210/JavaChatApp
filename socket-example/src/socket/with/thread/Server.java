package socket.with.thread;

import java.io.*;
import java.net.*;
import socket.with.thread.ClientHandler;
import chat.app.MyLogger;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(6666);
            MyLogger.info("Server started");
            while (true) {
                Socket client = server.accept();
                MyLogger.info("Server accepted");
                MyLogger.info(String.format("New client connected: '%s'", client.getInetAddress().getHostAddress()));
                ClientHandler clientHandler = new ClientHandler(client);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
