package DTO_DAO;

import java.util.Scanner;

public class ScoreProcess {
	Scanner sc = new Scanner(System.in);
	ScoreDAO dao = new ScoreDAO();


	public void insert() {
		int cnt = dao.stu_cnt();
		cnt++;
		do {
			System.out.println("입력 예 --> 홍길동 90 80 80");
			System.out.println(cnt+"번 학생 성적 입력 [이름 국어 영어 수학] --->");
			String name = sc.next();
			
			if(name.equals("-1")) {
				break;
			}
			
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int math = sc.nextInt();
			
			ScoreDTO obj = new ScoreDTO();
			obj.setName(name);
			obj.setKor(kor);
			obj.setEng(eng);
			obj.setMath(math);
			
			
		} while(true);
	} // insert()
	
	public void printScore() {
		dao.sort();
		System.out.println("------------------------------");
		System.out.println("전체 학생 수: "+dao.stu_cnt()+"명");
		System.out.println("------------------------------");
		System.out.println("이름  국어  영어  수학  총점  평균  ");
		System.out.println("------------------------------");
		
		for(ScoreDTO obj : dao.getLists()) {
			System.out.printf("%4s %5d %5d %5d %5d %.1f\n",
					obj.getName(),
					obj.getKor(),
					obj.getEng(),
					obj.getMath(),
					obj.getTotal(),
					obj.getAvg()
					);
		}
}
