package chat.app;

import java.io.*;
import java.net.*;

public class WriteThread implements Runnable{
    private PrintWriter out;
    private Socket socket;
    private Client client;

    public WriteThread(Socket socket, Client client){
        this.socket = socket;
        this.client = client;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        Console console = System.console();
        String userName = console.readLine("Enter your name: ");
        client.setUserName(userName);
        out.println(userName);
        String text;
        do {
            text = console.readLine(userName);
            out.println(text);
//            out.println();
        } while (!socket.isClosed());

        try {
            socket.close();
        } catch (IOException e) {
            MyLogger.info(String.format("Error waiting from server", e.getMessage()));
        }
    }
}
