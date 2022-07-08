package socket.with.thread;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 6666);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            String line = null;

            while (sc.hasNext()) {
                line = sc.nextLine();

                out.println(line);
                out.flush();
                MyLogger.info(String.format("Server replied '%s'", in.readLine()));

            }
            sc.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
