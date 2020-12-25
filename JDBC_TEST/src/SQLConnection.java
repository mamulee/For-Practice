import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	//JDBC를 사용하기 위한 변수 선언.
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:49161:xe";
	
	//DB 접속 아이디, 비밀번호.
	private static final String USER = "system"; //아이디
	private static final String PASS = "test"; //비밀번호
	private static Connection conn;
	
	//DB 연결 메소드 선언.
	public static final Connection connect() throws ClassNotFoundException, SQLException{
		// JDBC 드라이버 로딩
		Class.forName(JDBC_DRIVER);
		// 접속
		// Connection 객체 생성 + 접속 작업
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		System.out.println(conn.isClosed()? "접속 종료" : "접속 중");

		return conn;
	}
	
	//DB 연결 종료 메소드 선언.
	public static void close() throws SQLException{
		if(conn!=null) {
			conn.close();
			
			System.out.println(conn.isClosed()? "접속 종료" : "접속 중");
		}
	}
}
