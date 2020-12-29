package jdbc_prg;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class n04_JdbcEx03 {

	public static void main(String[] args) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott", pw = "scott";
		Connection con = DriverManager.getConnection(url, user, pw);
		
		System.out.println("DB 접속 성공!");
		
		Statement st = con.createStatement();
		
		String sql = "select * from member order by no";
		
		ResultSet rs = st.executeQuery(sql); // select문일 경우 사용
		
		while(rs.next()) {
			int no = rs.getInt(1); // 첫번째 열부터
			String name = rs.getString("name");
			String hp = rs.getString("hp");
			String addr = rs.getString("addr");
			System.out.println(no+" "+name+" "+hp+" "+addr);
		}
		
		if(rs != null) rs.close();
		if(st != null) st.close();
		if(con != null) con.close();
		
	}

}
