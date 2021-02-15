package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import db.DBConn;
import exception.MyException;
import javafx.application.Platform;
import model.AlertBox;
import model.KakaoMessage;
import model.MessagePane;
import model.MyMessagePane;
import model.UserDTO;

public class UserDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// 회원가입
	public int signUp(UserDTO dto) throws MyException {
		int result = 0;
		conn = DBConn.getConnection();
		String sql = "INSERT INTO kakaoUser VALUES (user_seq.nextval, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPhonenum());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPassword());

			result = pstmt.executeUpdate();
			AlertBox.display("회원 가입", dto.getName()+"님 환영합니다!");
			System.out.println(result + "명 회원가입 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}

		return result;
	} // signUp()

	// 로그인
	public boolean login(String phonenum, String pw) throws MyException {
		boolean result = false;
		conn = DBConn.getConnection();
		String sql = "SELECT * FROM kakaoUser WHERE phonenum = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phonenum);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = true;
				UserDTO.nowUser.setUser_num(rs.getInt("user_num"));
				UserDTO.nowUser.setPhonenum(rs.getString("phonenum"));
				UserDTO.nowUser.setName(rs.getString("name"));
				UserDTO.nowUser.setPassword(rs.getString("password"));

				// 친구 목록
				sql = "SELECT * FROM kakaoUser WHERE phonenum != ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, phonenum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					UserDTO dto = new UserDTO();
					dto.setName(rs.getString("name"));
					dto.setPassword(rs.getString("password"));
					dto.setPhonenum(rs.getString("phonenum"));
					dto.setUser_num(rs.getInt("user_num"));
					UserDTO.friends.add(dto);
				}
				System.out.println("친구목록추가 완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		return result;
	}

	// 채팅방 목록 및 순서
	public int isChatExist (UserDTO nowUser, UserDTO friend) {
		int a, b; //작은번호가 a 큰번호 b
		int result = 0;
		if(nowUser.getUser_num() > friend.getUser_num()) {
			a = friend.getUser_num();
			b = nowUser.getUser_num();
		}else {
			a = nowUser.getUser_num();
			b = friend.getUser_num();
		}

		conn = DBConn.getConnection();
		String sql = "SELECT DISTINCT cm.room_num, cr.user1_num, cr.user2_num FROM chatmessage cm JOIN chatroom cr ON cm.room_num = cr.room_num WHERE user1_num = ? and user2_num = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			rs = pstmt.executeQuery();
			if(rs.next()) {//방있으면
				System.out.println("이전 대화 내용 있음");
				result = rs.getInt("room_num");
				System.out.println("방번호: "+result);
			} else {
				result = -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // isChatExist

	// 채팅방 최신 순
	public ArrayList<Integer> roomOrder() {
		ArrayList<Integer> roomNum = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql = "SELECT DISTINCT room_num FROM (SELECT room_num, max(message_time) latest FROM chatmessage GROUP BY room_num) ORDER BY latest desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				roomNum.add(rs.getInt("room_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}

		return roomNum;
	}

	// 채팅방 있으면 방번호리턴, 없으면 만들어서 방번호 리턴
	public int roomCheck(UserDTO nowUser, UserDTO friend) {
		int a, b; //작은번호가 a 큰번호 b
		int result = 0;
		if(nowUser.getUser_num() > friend.getUser_num()) {
			a = friend.getUser_num();
			b = nowUser.getUser_num();
		}else {
			a = nowUser.getUser_num();
			b = friend.getUser_num();
		}

		conn = DBConn.getConnection();
		try {
			String sql = "SELECT room_num FROM chatroom where user1_num = ? and user2_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			rs = pstmt.executeQuery();
			if(rs.next()) {//방있으면
				System.out.println("방있음");
				result = rs.getInt("room_num");
				System.out.println("방번호: "+result);
			}else {//방 없으면
				System.out.println("방없어서 방생성");
				sql = "INSERT INTO chatroom VALUES(chatroom_seq.NEXTVAL, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, a);
				pstmt.setInt(2, b);
				pstmt.execute();//값넣기

				sql = "SELECT room_num FROM chatroom where user1_num = ? and user2_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, a);
				pstmt.setInt(2, b);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt("room_num");
				}
				System.out.println("방번호: "+result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		return result;
	}//roomCheck

	public void chatLog(KakaoMessage msg) {

		int user_num = msg.getSendUserNum();
		int room_num = msg.getRoom_num();

		conn = DBConn.getConnection();
		String sql = "INSERT INTO chatMessage VALUES(chatMessage_seq.NEXTVAL, ?, SYSDATE, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, msg.getSendComment());
			pstmt.setInt(2, user_num);
			pstmt.setInt(3, room_num);
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
	}

	public ArrayList<KakaoMessage> chatHistory (int count) {
		int messageCnt = count;
		ArrayList<KakaoMessage> messageArr = new ArrayList<>();		
		conn = DBConn.getConnection();
		try {
			String sql = "SELECT message_num, message, to_char(MESSAGE_TIME, 'YYYYMMDDHH24MI') time, user_num, ROOM_NUM "
					+ "FROM (SELECT * FROM CHATMESSAGE ORDER BY MESSAGE_TIME DESC) "
					+ "WHERE rownum <= ? and ROOM_NUM = ? ORDER BY  MESSAGE_time ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageCnt);
			pstmt.setInt(2, Chat_w_01_controller.room_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				KakaoMessage kakaoMessage = new KakaoMessage();
				kakaoMessage.setSendComment(rs.getString("message"));
				kakaoMessage.setRoom_num(rs.getInt("room_num"));
				kakaoMessage.setSendUserNum(rs.getInt("user_num"));
				kakaoMessage.setTime(rs.getString("time"));
				messageArr.add(kakaoMessage);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.dbClose(rs, pstmt, conn);
		}
		return messageArr;
	}


} // UserDAO
