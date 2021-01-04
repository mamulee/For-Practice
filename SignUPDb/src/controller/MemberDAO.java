package controller;

import java.sql.*;
import java.util.ArrayList;

import db.SQLConnection;
import exception.MyException;
import model.MemberDTO;

public class MemberDAO {

	// 회원가입
	public int insertData(MemberDTO dto) throws MyException {
		Connection con = SQLConnection.connect();
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member(tel, name, pw)"
				+ "VALUES(?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getTel());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPw());

			result = pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e1) {
			throw new MyException("이미 있는 번호입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				// if(con != null) con.close();
				SQLConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	} // insert

	// 회원 조회
	public ArrayList<MemberDTO> getList() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = SQLConnection.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberDTO dto = new MemberDTO();

				dto.setTel(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setPw(rs.getString(3));

				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				SQLConnection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	} // getList()

	// 회원 존재 여부
	public boolean isExist(String tel) {
		boolean result = false;
		Connection con = SQLConnection.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE tel = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				SQLConnection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	} // isExist()

	// 회원 로그인 확인
	public boolean login(String tel, String pw) throws MyException {
		boolean result = false;

		if (!isExist(tel)) {
			throw new MyException("존재하지 않는 번호입니다.");
		} else {

			MemberDTO dto = new MemberDTO();
			Connection con = SQLConnection.connect();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM member WHERE tel = ?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tel);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					if(pw.equals(rs.getString(3))) {
						result = true;
					} else {
						throw new MyException("비밀번호가 틀렸습니다.");
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					SQLConnection.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;

	} // login()




} // class end