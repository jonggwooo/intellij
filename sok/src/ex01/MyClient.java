package ex01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class MyClient {

    public MyClient() throws IOException {
        // 1. 서버 연결 (localhost == 127.0.0.1) ==> 192.168.200.176  자기 컴퓨터주소
        Socket socket = new Socket("127.0.0.1",10000);

        // 2. 서버쪽으로 메세지 전송
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
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
