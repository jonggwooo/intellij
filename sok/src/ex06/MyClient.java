package ex06;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

    public MyClient() throws IOException {
        // 1. 서버 연결
        Socket socket = new Socket("192.168.200.58", 10000);

        // 2. 버퍼 생성
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "utf-8")
        );
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), "utf-8")
        );
        Scanner keyboard = new Scanner(System.in, "utf-8");

        // 쓰기 스레드
        new Thread(() -> {
            try {
                while(true){
                    String msg = keyboard.nextLine();
                    writer.write(msg);
                    writer.write("\n");
                    writer.flush();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        // 읽기 스레드
        new Thread(() -> {
            try {
                while(true){
                    String msg = reader.readLine(); // 버퍼 비우기
                    System.out.println("ssar99 : "+msg);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

    }

    public static void main(String[] args) {
        try {
            new MyClient();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}