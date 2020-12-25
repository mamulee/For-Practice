import java.sql.*;

public class DBTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection conn =null;

		Statement stmt = null; 			// 테이블 생성 SQL문장
		PreparedStatement pstmt = null;		// 매개변수를 이용한 SQL 문장
		Statement stmt2 = null; 		// SELECT문
		ResultSet rs = null; 			// SELECT 결과 저장 ResultSet 변수
		String sql = "";			// SQL문 저장 변수

		// SQL 관련 메소드 사용시 SQLException 예외를 처리해야 함
		try {
			conn = SQLConnection.connect();

			System.out.println(conn);    // oracle.jdbc.driver.T4CConnection@27f8302d

			// Statement 객체 생성
			stmt = conn.createStatement();

			// 테이블 생성 SQL문장 작성
			sql = "CREATE TABLE MyTEST(name varchar2(20), age number)";

			// sql 문장 실행 및 반환 값 저장
			int create = stmt.executeUpdate(sql);

			// 결과 확인
			System.out.println("테이블 생성 성공?? : " + create);    // 테이블 생성 성공??: 0

			// INSERT문, 테이블에 데이터 입력
			sql = "INSERT INTO MyTEST VALUES(?, ?)";

			// SQL문장을 매개변수로 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// 첫 번째 매개변수(?)에 "홍길동" 전달
			pstmt.setString(1, "홍길동");

			// 두 번째 ?에 34 전달
			pstmt.setInt(2, 34);

			// INSERT문 실행 및 반환 값 저장
			int insert = pstmt.executeUpdate();

			// 결과 확인
			System.out.println("입력 데이터 개수: " + insert);    // 입력 데이터 개수: 1

			// SELECT문, 테이블 내 데이터 조회
			stmt2 = conn.createStatement();

			// Query 반환값 ResultSet 객체 반환
			rs = stmt2.executeQuery("SELECT * FROM MyTEST");

			// 각각의 데이터를 담을 변수 선언
			String name; 	// 이름
			int age; 		// 나이

			// ResultSet의 다음 로우가 있는 동안 계속 데이터 조회
			while (rs.next()) {
				// name에 "name" 컬럼 데이터 저장
				name = rs.getString("name");

				// age에 "age" 컬럼 데이터 저장
				age = rs.getInt("age");

				// 출력
				System.out.println("이름: " + name + ", 나이: " + age);
				// 이름: 홍길동, 나이: 34
			}

		} catch (Exception e) {
			System.out.println(e);

			// 예외와 상관없이 메모리에서 해제하기 위해 finally문에 작성
		} finally {
			// close() 역시 예외 처리가 필요함
			try {
				// 메모리 해제
				rs.close();
				stmt2.close();
				pstmt.close();
				stmt.close();
				SQLConnection.close();

			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}

}
