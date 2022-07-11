package chat.app;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
    ServerSocket server = null;
    public Server(int port) {
        this.port = port;
    }
    void addUserName(String userName) {
        userNames.add(userName);
    }


    public void execute() {
        try {
            server = new ServerSocket(port);
            MyLogger.info(String.format("Chat server is on port '%s'", port));
            while (true) {
                Socket socket = server.accept();
                MyLogger.info("Server connected");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                new Thread(newUser).start();

            }
        } catch (IOException e) {
            MyLogger.info(String.format("Error in this server: '%s'", e.getMessage()));
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter port number to continue");
            System.exit(0);
        }
        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        server.execute();
    }

    void broadcast(String msg, UserThread ex) {
        for (UserThread aUser: userThreads
             ) {
            if (aUser != ex) {
                aUser.sendMsg(msg);
            }
        }
    }

    boolean hasUser() {
        return !this.userNames.isEmpty();
    }

    Set<String> getUserNames() {
        return this.userNames;
    }

    public void removeUser(String userName, UserThread userThread) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(userThread);
            MyLogger.info(String.format("The user '%s' exit", userName));
        }
    }
}
