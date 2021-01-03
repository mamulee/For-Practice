package db;

import java.sql.*;

public class SQLConnection {
	private static final String JDBC_Driver = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	private static final String user = "practice";
	private static final String pw = "practice";
	private static Connection con;

	public static final Connection connect() {
		try {
			Class.forName(JDBC_Driver);

			con = DriverManager.getConnection(DB_URL, user, pw);

			System.out.println(con.isClosed()? "접속 종료" : "접속 중");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close() {
		if(con != null) {
			try {
				con.close();
				
				System.out.println(con.isClosed()? "접속 종료" : "접속 중");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
