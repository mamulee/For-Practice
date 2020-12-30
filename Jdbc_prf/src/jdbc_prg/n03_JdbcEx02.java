package jdbc_prg;

import java.io.*;
import java.sql.*;

public class n03_JdbcEx02 {

	Connection con;
	Statement stmt;
	BufferedReader buf;
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "scott", pw = "scott";
	
	public n03_JdbcEx02() throws Exception {
		dbConn();
		makeSql();
		close();
	}
	
	public void dbConn() {
		try {
			// OracleDriver 를 메모리에 로딩 및 DriverManager에 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
			System.out.println("DB 접속 성공!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void makeSql() throws SQLException, IOException {
		buf = new BufferedReader(new InputStreamReader(System.in));
	
		String sql ="";
		System.out.println("SQL문을 입력하세요:");
		stmt = con.createStatement();
		
		while ((sql = buf.readLine()) != null) {
			int cnt = stmt.executeUpdate(sql.trim());
			System.out.println(cnt + "개의 레코드가 수정되었음.");
		} // while
		
	} // makeSql()

	public void close() throws Exception {
		if(con != null) con.close();
		if(stmt != null) stmt.close();
		if(buf != null) buf.close();
	}
	
	public static void main(String[] args) {
		try {
			new n03_JdbcEx02();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
