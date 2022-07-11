package socket.with.thread;


import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class MyLogger {
    public static void info(String msg) {
        Thread currentThread = Thread.currentThread();

        System.out.println(String.format("%s [%s] [%s] [%s] INFO - %s ",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(OffsetDateTime.now()),
                        currentThread.getName(),
                        currentThread.isDaemon() ? "Daemon Thread" : "User Thread",
                        currentThread.getPriority(),
                        msg));
    }


}
