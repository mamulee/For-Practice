package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import exception.MyException;
import exception.SignUpException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MemberDTO;

public class Signup_controller implements Initializable{
	@FXML private Label signup_time;
	@FXML private TextField signup_signup_phonenum;
	@FXML private TextField signup_signup_name;
	@FXML private PasswordField signup_signup_password;
	@FXML private PasswordField signup_signup_passwordcheck;
	@FXML private Button signup_signup_btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signup_signup_btn.setOnAction(e->{
			try {
				handleBtnsignup(e);
			} catch (MyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		signup_time.setText(sdf.format(date));

	}
	
	public void handleBtnsignup (ActionEvent event) throws MyException {

		MemberDAO dao = new MemberDAO();
		SignUpException sue = new SignUpException();
		boolean tel = true;
		boolean name = true;
		boolean pw = true;
		String pw2;

		try {
			MemberDTO dto = new MemberDTO();

			do { // 전화번호
				try {
					dto.setTel(signup_signup_phonenum.getText());
					sue.telCheck(dto.getTel());

					tel = false;

				} catch(MyException e) {
					System.out.println(e.getMessage());
				}
			}while(tel);

			do { // 이름
				dto.setName(signup_signup_name.getText());

				name = false;

			}while(name);

			do { // 비밀번호
				try {
					dto.setPw(signup_signup_password.getText());
					pw2 = signup_signup_passwordcheck.getText();

					sue.pwCheck(dto.getPw(), pw2);
					pw = false;

				} catch (MyException e) {
					System.out.println(e.getMessage());
				}
			}while(pw);

			int result = dao.insertData(dto);

			if(result != 0) {
				Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));
				Scene scene = new Scene(login);
				Stage primaryStage = (Stage) signup_signup_btn.getScene().getWindow();
				primaryStage.setScene(scene);
			}else {
				System.out.println("회원가입 실패,,,");
			}


		} catch(Exception e) {
			System.out.println(e.toString());
		}




	} // handleBtnsignup
}
