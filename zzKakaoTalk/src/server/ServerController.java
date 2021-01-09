package server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.Chat_w_01_controller;
import controller.KakaoMain_controller;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Message;
import model.UserDTO;
import server.ServerController.Client;

public class ServerController implements Initializable{

	@FXML private Label Chats_time;
	@FXML private Slider chat_slider_opacity;
	@FXML private BorderPane server_main;
	@FXML private TextArea logText;
	@FXML private Button btnServerStart;
	@FXML private TextField portField;

	KakaoMain_controller kc = new KakaoMain_controller();

	ExecutorService executorService;
	ServerSocket serverSocket;
	List<Client> connections = new Vector<>();
	int server_no;

	public void StartServer() {
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()
				);
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
		}catch (Exception e) {
			if(!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}

		//연결 수락 객체
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Platform.runLater(() -> {
					log("[Server Start]");
					btnServerStart.setText("stop");
				});

				while (true) {
					try {
						Socket socket = serverSocket.accept();
						String message = "[Connection accept : " + 
								socket.getRemoteSocketAddress() + ": " +
								Thread.currentThread().getName() + "]";

						Platform.runLater(() -> 
						log(message)
								);
						Client client = new Client(socket);
						connections.add(client);
						Platform.runLater(() -> log(
								"[Connections' size : " + connections.size() + "]"
								));
					}catch (IOException e ) {
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		executorService.submit(runnable);
	}//end StartServer()

	void stopServer() {
		try {
			Iterator<Client> iterator = connections.iterator();
			while (iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if (executorService != null && !executorService.isShutdown()) {
				executorService.shutdown();
			}
			Platform.runLater(() ->{
				log("[Server stop]");
				btnServerStart.setText("start"); //server 버튼 변경
			});
		}catch(IOException e) {}
	}


	public class Client {
		Socket socket;
		String userName;
		
		public Client (Socket socket) {
			this.socket = socket;
			receive();
		}

		//메세지 받기 메소드
		void receive () {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while(true) {
							byte[] byteArr = new byte[1024];
							InputStream is = socket.getInputStream();

							int readByteCount = is.read(byteArr);

							if(readByteCount == -1) {
								throw new IOException();
							}

							String message = "[Request : " + socket.getRemoteSocketAddress()
							+ ":" + Thread.currentThread().getName() + "]" ;

							// new
							Message ms = toObject(byteArr, Message.class);

							Platform.runLater(() -> 
							log(message));

//							String data = new String (byteArr, 0, readByteCount, "UTF-8");
//
//							for (Client client : connections) {
//								client.send(data);
//							}

							// new
							userName = ms.getSendUserName();
							System.out.println(userName + "qq");
							send(byteArr); // 본인한테 보냄
							for (Client client : connections) { // 모든 클라이언트에게 보냄
								System.out.println(client.userName + "ss" + ms.getReceiveFriendName());
								if (client.userName != null) {
									if (client.userName.equals(ms.getReceiveFriendName())
											&& !ms.getSendUserName().equals(ms.getReceiveFriendName())) {
										client.send(byteArr);
									}
								}
							}
							

						}
					}catch (Exception e) {
						try {
							connections.remove(Client.this);
							String message = "[Client connection Error] :" +
									socket.getRemoteSocketAddress() + " : " + Thread.currentThread().getName() + "]";
							Platform.runLater(() -> 
							log(message)
									);
							socket.close();
						}catch (IOException e1) {}
					}
				}
			};
			executorService.submit(runnable);
		}

		// 새로 추가
		private Message toObject(byte[] byteArr, Class<Message> class1) {

			Object obj = null;
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
				ObjectInputStream ois = new ObjectInputStream(bis);
				obj = ois.readObject();
			} catch (Exception e) {
			}
			return class1.cast(obj);
		}


		//클라이언트한테 데이터 보냄
		// NEW 수정
		public void send (byte[] bytes) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						OutputStream os = socket.getOutputStream();
						os.write(bytes);
						os.flush();
					} catch (Exception e) {
						try {
							String message = "[Client connection Error : " 
									+ socket.getRemoteSocketAddress()+ " : " 
									+ Thread.currentThread().getName() + "]";
							Platform.runLater(() -> 
							log(message)
									);
							connections.remove(Client.this);
							socket.close();
						}catch (IOException e1) {}
					}
				}
			};
			executorService.submit(runnable);
		}
		
		public void send (String data) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						byte [] byteArr = data.getBytes("UTF-8");
						OutputStream os = socket.getOutputStream();
						os.write(byteArr);
						os.flush();
					} catch (Exception e) {
						try {
							String message = "[Client connection Error : " 
									+ socket.getRemoteSocketAddress()+ " : " 
									+ Thread.currentThread().getName() + "]";
							Platform.runLater(() -> 
								log(message)
							);
							connections.remove(Client.this);
							socket.close();
						}catch (IOException e1) {}
					}
				}
			};
			executorService.submit(runnable);
		}
	
	} // Client
	
	public void log (String msg) {
		logText.appendText(msg + "\n");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Chats_time.setText(sdf.format(date));

		chat_slider_opacity.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
				server_main.setOpacity(chat_slider_opacity.getValue() /100.0);
			}
		});
		btnServerStart.setOnAction(e -> handleServerStart(e));
	}

	public void handleServerStart (ActionEvent event) {
		if (btnServerStart.getText().equals("start")) {
			StartServer();
		}else if ( btnServerStart.getText().equals("stop")) {
			stopServer();
		}
	}



}
