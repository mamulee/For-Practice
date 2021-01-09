package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Message;
import model.UserDTO;

public class Chat_w_01_controller implements Initializable{
	@FXML private Label Chats_time;
	@FXML private Label chat_chat_name_label;
	@FXML private BorderPane chat_w_01_mainpane;
	@FXML private Pane chat_w_01_pane;
	@FXML private Slider chat_slider_opacity;
	@FXML private TextField chat_write_messages;
	@FXML private TextArea chat_textarea;
	@FXML private Button chat_send_button;
	@FXML private Button chat_back_btn;
	@FXML private Button chat_start_button;

	public static int room_num;

	Socket socket;

	void startClient () {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress("localhost", 5001));
					Platform.runLater(() -> {
						chat("[Connection]" 
								+ socket.getRemoteSocketAddress() + "]");
						chat_start_button.setText("stop");
						chat_send_button.setDisable(false);

					});
				}catch (IOException e) {
					Platform.runLater(() -> chat("startClient() : [Connection Error]"));
					if(!socket.isClosed()) {stopClient();}
					return;
				}
				receive();
			}
		};
		thread.start();
	}

	void stopClient() {
		try {
			Platform.runLater(() -> {
				chat("stopClient() : [Connection Error]");
				chat_start_button.setText("start");
				chat_send_button.setDisable(true);
			});

			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		}catch (Exception e) {}
	}

	void receive () {
		while (true) {
			try {
				byte [] byteArr = new byte [1024];
				InputStream is = socket.getInputStream();

				int readByteCount = is.read(byteArr);
				if (readByteCount == -1) {
					throw new IOException();
				}

				String data = new String (byteArr, 0, readByteCount, "UTF-8");

				// new 
				Message message = toMessage(byteArr, Message.class);
				Platform.runLater(() -> chat_textarea.appendText(message+"\n"));

			}catch (Exception e) {
				Platform.runLater(() -> chat("receive() : [Connection Error]"));
				stopClient();
				break;
			}
		}
	}

	// NEW
	private Message toMessage(byte[] recvBuffer, Class<Message> class1) {

		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(recvBuffer);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (Exception e) {
		}
		return class1.cast(obj);
	}

	// NEW 수정
	public void send(Message messageInfo) {

		Thread thread = new Thread(()->{
			// 객체를 byte array로 변환
			byte[] bytes = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream(); // 바이트 배열에 데이터를 입출력하는데 사용되는 스트림.
			// 데이터를 임시로 바이트 배열에 담아서 변환 등 작업 사용
			try {
				ObjectOutputStream oos = new ObjectOutputStream(bos); // 객체를 직렬화.
				oos.writeObject(messageInfo); // 객체를 직렬화하기 위해 메소드 사용
				oos.flush(); // 버퍼에 잔류하는 모든 바이트 출력
				oos.close();
				bos.close();
				bytes = bos.toByteArray(); // byteArray로 변환
			} catch (IOException e) {
			}
			// message객체를 byte로 변환 후 소켓을 통해 보냄
			try {
				byte[] data = bytes;
				OutputStream outputStream = socket.getOutputStream(); // 출력 스트림 얻기.
				outputStream.write(data);
				outputStream.flush();
				Platform.runLater(() -> chat("[send Success]"));
				System.out.println("서버로 보내기 완료!");
			} catch (IOException e) {
				Platform.runLater(() -> chat("[Server connection Error]"));
				System.out.println("서버로 통신 안됨");
				e.printStackTrace();
			}
		});
		thread.start();
	}
	
//	void send (String data) {
//		Thread thread = new Thread () {
//			public void run() {
//				try {	
//					String withName = UserDTO.nowUser.getName()+":\n"+data;
//					byte[] byteArr = withName.getBytes("UTF-8");
//					OutputStream os = socket.getOutputStream();
//					os.write(byteArr);
//					os.flush();
//					Platform.runLater(() -> chat("[send Success]"));
//				}catch (Exception e) {
//					Platform.runLater(() -> chat("[Server connection Error]"));
//				}
//			}
//		};
//		thread.start();
//	}


	public void chat (String msg) {
		chat_textarea.appendText(msg+"\n");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chat_send_button.setOnAction(e->handleBtnSend(e));
		chat_back_btn.setOnAction(e->handleBtnBack(e));
		chat_start_button.setOnAction(e->handleBtnStart(e));

		chat_chat_name_label.setText(UserDTO.withFriend.getName());

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Chats_time.setText(sdf.format(date));

		chat_slider_opacity.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
				chat_w_01_mainpane.setOpacity(chat_slider_opacity.getValue() /100.0);
			}
		});

	}

	public void handleBtnStart (ActionEvent event) {
		if(chat_start_button.getText().equals("start")) {
			startClient();
		} else if(chat_start_button.getText().equals("stop")){
			stopClient();
		}
	}

	public void handleBtnBack(ActionEvent event) {
		try {
			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Chats.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) chat_back_btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String writeMessages () {
		return chat_write_messages.getText();
	}


	// NEW 수정
	public void handleBtnSend(ActionEvent event) {

		Message message = null;
		message = new Message(UserDTO.nowUser.getName(), chat_write_messages.getText(), LocalTime.now(),
				"text", UserDTO.withFriend.getName());
		send(message);
//		send(chat_write_messages.getText());
//		chat_write_messages.setText("");
	}





}
