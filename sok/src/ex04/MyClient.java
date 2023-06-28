package ex04;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 클라이언트는 read 버퍼를 추가로 만들고
 * 서버는 write 버퍼를 추가로 만든다
 */
public class MyClient {

    public MyClient() throws IOException {
        // 1. 서버 연결
        Socket socket = new Socket("127.0.0.1", 10000);

        // 2. 버퍼 생성 (송수신 선 만듬)
        Scanner keyboard = new Scanner(System.in);

        PrintWriter request = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
        BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")
        );

        while(true){
            // 3. 키보드로 부터 입력 대기
            String requestBody = keyboard.nextLine();

            // 4. 서버로 요청
            request.println(requestBody);

            // 5. 서버로부터 응답
            String responseBody = response.readLine();
            System.out.println("서버로부터 받은 메시지 : "+responseBody);
        }

    }

    public static void main(String[] args) {
        try {
            new MyClient();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}