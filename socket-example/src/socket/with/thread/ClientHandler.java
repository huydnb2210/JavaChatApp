package socket.with.thread;

import java.net.*;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String line = "";
            while ((line = in.readLine()) != null) {
                MyLogger.info(String.format("Sent from the client: '%s' \n", line));
                out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
