package chat.app;

import java.net.*;
import java.io.*;

public class UserThread implements Runnable{
    private Socket socket;
    private Server server;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public UserThread (Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            printUsers();

            String userName = in.readLine();
            server.addUserName(userName);

            String serverMsg = "New user connected: " + userName;
            server.broadcast(serverMsg, this);

            String clientMsg;
            do {
                clientMsg = in.readLine();
                serverMsg = "[" + userName + "]: " + clientMsg;
                server.broadcast(serverMsg, this);
            } while(!clientMsg.equals("Exit"));
            server.removeUser(userName, this);
            socket.close();
<<<<<<< HEAD
=======

            serverMsg = userName + " exit.";
            server.broadcast(serverMsg, this);

>>>>>>> Huy
        } catch (IOException e) {
            MyLogger.info(String.format("Error in UserThread: '%s'", e.getMessage()));
            e.printStackTrace();
        }
    }

    void printUsers() {
        if(server.hasUser()) {
            MyLogger.info(String.format("Connected users: '%s' ", server.getUserNames()));
        }
        else {
            MyLogger.info("No other user connected");
        }
    }

    void sendMsg(String msg) {
        out.println(msg);
    }
}
