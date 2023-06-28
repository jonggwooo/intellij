package ex03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// HDX - 스레드가 필요 없다.
public class MyServer {

    public MyServer() throws IOException {
        // 1. 서버 대기중
        ServerSocket serverSocket = new ServerSocket(10000);
        Socket socket =  serverSocket.accept(); // 리스너 대기중
        System.out.println("클라이언트가 연결되었습니다");

        // 2.서버 요청 받음
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "utf-8")
        );
        String msg = reader.readLine(); // 버퍼 비우기
        System.out.println("클라이언트에게서 요청이 왔습니다 : "+msg);

        // 3. 구문 분석 (파싱)
        String responseBody = "";
        if(msg.equals("text/html")) {
            responseBody = "<html><h1>Hello</h1></html>";
        }else if(msg.equals("text/plain")){
            responseBody = "Hello";
        }else{
            responseBody = "404 not found";
        }

        // 4. 서버 응답
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), "utf-8")
        );
        writer.write(responseBody);
        writer.write("\n");
        writer.flush();
    }

    public static void main(String[] args) {
        try {
            new MyServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}