package p01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//서버
public class MySocketServer {

	public static void main(String[] args) {
		try {
			int socketPort = 1234;
			ServerSocket serverSocket = new ServerSocket(socketPort); // 소켓 만들기
			Socket socketUser = null; // 클라이언트 접속시 사용할 Socket
			System.out.println("socket : " + socketPort + "으로 서버가 열렸습니다");

			// 소켓 서버가 종료될 때까지 반복
			while(true) {

				socketUser = serverSocket.accept(); 
				// 소켓 서버로 접속 시 socketUser에 접속자 정보 할당
				System.out.println("Client가 접속함 : " + socketUser.getLocalAddress()); 
				// 접속자의 getLocalAddress 가져오기

				// InputStream - 클라이언트에서 서버로
				InputStream input = socketUser.getInputStream(); 
				// socket의 InputStream 정보를 InputStream in에 넣은 뒤
				BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
				// BufferedReader에 위 InputStream을 담아 사용

				System.out.println(reader.readLine()); 
				// 클라이언트에서 온 메세지 확인

				// OutputStream - 서버에서 클라이언트로
				OutputStream out = socketUser.getOutputStream(); 
				// socket의 OutputStream 정보를 OutputStream out에 넣은 뒤
				PrintWriter writer = new PrintWriter(out, true); 
				// BufferedReader에 위 InputStream을 담아 사용

				writer.println("SERVER TO CLIENT"); 
				// 서버에서 클라이언트로 메세지 보내기

			}			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}