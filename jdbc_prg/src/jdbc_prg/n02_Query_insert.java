package jdbc_prg;

import java.sql.*;

public class n02_Query_insert {

	public static void main(String[] args) {
		// 1. jdbc 드라이버를 메모리에 로딩
		// 2. jdbc 드라이버 객체를 만듦
		// 3. 드라이버 객체를 DriverManager에 등록
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "scott";
			
			Connection c = DriverManager.getConnection(url, user, pw);
			System.out.println("데이터베이스에 접속 성공!");
		
			// 질의문 전송을 위한 Statement 객체 생성
			Statement st = c.createStatement();
			String sql = "insert into member values(6, '장동민', '010-212-2121', '서울 마포구 아현동')";
			
			int cnt = st.executeUpdate(sql); // 몇 개의 레코드를 넣었는지
			System.out.println(cnt+"개 행이 삽입되었습니다.");
			
			if(st !=null) st.close();
			if(c !=null) c.close();
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
