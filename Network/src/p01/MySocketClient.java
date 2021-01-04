package p01;

import java.io.*;
import java.net.Socket;

//클라이언트
public class MySocketClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 1234); // 소켓 서버에 접속 
			System.out.println("socket 서버에 접속 성공!");

			// OutputStream - 클라이언트에서 Server로 메세지 발송
			OutputStream out = socket.getOutputStream(); 
			// socket의 OutputStream 정보를 OutputStream out에 넣은 뒤 
			PrintWriter writer = new PrintWriter(out, true); 
			// PrintWriter에 위 OutputStream을 담아 사용

			writer.println("CLIENT TO SERVER"); 
			// 클라이언트에서 서버로 메세지 보내기

			// InputStream - Server에서 보낸 메세지 클라이언트로 가져옴
			InputStream input = socket.getInputStream(); 
			// socket의 InputStream 정보를 InputStream in에 넣은 뒤
			BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
			// BufferedReader에 위 InputStream을 담아 사용

			System.out.println(reader.readLine()); 
			// 서버에서 온 메세지 확인
			System.out.println("CLIENT SOCKET CLOSE");
			socket.close(); // 소켓 종료

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
