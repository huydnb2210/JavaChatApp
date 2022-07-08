package chat.app;

import java.io.*;
import java.net.*;

public class Client {
    private int port;
    private String hostName;



    String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostName, port);
            MyLogger.info("Connected to the server");
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();


        } catch (UnknownHostException ex) {
            MyLogger.info(String.format("Server not found: '%s'", ex.getMessage()));
            ex.printStackTrace();
        }
        catch (IOException e) {
            MyLogger.info(String.format("I/O error: '%s'", e.getMessage()));
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {System.out.println("Please enter address and port number");
            System.exit(0);}

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        Client client = new Client(hostname, port);
        client.execute();
    }
}
