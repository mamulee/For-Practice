package controller;

import java.sql.*;
import java.util.ArrayList;

import db.SQLConnection;
import model.MemberDTO;

public class MemberDAO {

	// 회원가입
	public int insertData(MemberDTO dto) {
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

}
