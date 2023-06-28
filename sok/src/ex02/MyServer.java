package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {


    public MyServer() throws IOException {
        // 1. 서버 소켓 (리스너) 생성
        ServerSocket serverSocket = new ServerSocket(10000);
        System.out.println("서버소켓이 클라이언트 연결 대기중");
        Socket socket = serverSocket.accept();

        System.out.println("클라이언트가 연결되었습니다");

        // 2. 클라이언트의 메세지를 받기
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        String msg = reader.readLine(); // 버퍼 비우기
        System.out.println("받은 메세지 : "+msg);
    }

    public static void main(String[] args) {
        try {
            new MyServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
