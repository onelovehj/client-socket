package org.example;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class MyClientSocket {

    Socket socket;
    BufferedWriter writer;
    //String DSERVER_IP;
    String serverIP;
    String uuid;
    Date now;
    public MyClientSocket() {
        try {
            serverIP = System.getenv("DSERVER_IP");
            socket = new Socket(serverIP,4432); // localhost: 아이피 주소, 4432: 포트 번호.
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String nowFormat = simpleDateFormat.format(now);
            uuid = UUID.randomUUID().toString();
            System.out.println( nowFormat + " " + uuid );
            writer.write(nowFormat + " " + uuid + "\n"); // 메세지 끝이라는걸 알려줘야함 "\n"으로,, 버퍼에 담은 거다.
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer ();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new MyClientSocket();

            }
        }, 0, 2000);

    }
}