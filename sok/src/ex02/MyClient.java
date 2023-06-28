package ex02;

import java.io.*;
import java.net.Socket;

//키보드에 버퍼를 연결해서 --> 키보드로 메세지를 서버쪽으로 전송
public class MyClient {

    public MyClient() throws IOException {
        // 1. 서버 연결 (localhost == 127.0.0.1) ==> 192.168.200.176  자기 컴퓨터주소
        Socket socket = new Socket("127.0.0.1",10000);

        // 2. 서버쪽으로 메세지 전송
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );

        // 3. 키보드 버퍼 연결 - 키보드 메세지를 받아서
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        ); // while 대기
        String msg = reader.readLine(); // 버퍼 비우기
        writer.write("Hello"); // 버퍼 8192바이트
        writer.write("\n");
        writer.flush();
    }

    public static void main(String[] args) {

        try{
          new MyClient();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
