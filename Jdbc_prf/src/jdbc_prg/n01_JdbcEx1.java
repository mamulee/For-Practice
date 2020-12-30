package jdbc_prg;

import java.sql.*;

public class n01_JdbcEx1 {

	public static void main(String[] args) {
	
		// 1. 드라이버 로딩
		// java.lang 패키지에 있는 클래스, Class,를 이용하여 jdbc 드라이버 로딩
		// Class.forName("클래스 이름") - 동적 바인딩 메서드 (프로그램 실행 시 드라이버 로딩)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			System.out.println("드라이버를 로딩했습니다.");
			// 2. 데이터베이스와 연결
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"; // thin = 대략 자바로만 이뤄졌다는 뜻
			String user = "scott";
			String pw = "scott";
			
			Connection con = DriverManager.getConnection(url, user, pw);
			
			System.out.println("데이터 베이스에 접속 성공!");
			
			// 3. 데이터 베이스에 sql문 전송을 위한 쿼리문 생성 (Statement 객체)
			String sql = "select ename from emp";
			Statement st = con.createStatement();
			
			// 4. 데이터 베이스에 sql문 전송
			ResultSet rs = st.executeQuery(sql);
			
			// 5. 데이터를 가져와서 출력
			while(rs.next()) {
				String col1 = rs.getString(1);
				System.out.println(col1);
			}
			
			// 6. 데이터 베이스와 연결된 자원 반납
			if(con != null) con.close();
			if(st != null) st.close();
			if(rs != null) rs.close();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}

}
