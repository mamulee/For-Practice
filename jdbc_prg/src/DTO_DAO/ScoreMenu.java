package DTO_DAO;

import java.util.Scanner;

public class ScoreMenu {
	// 콘솔 화면에 메뉴를 출력하는 메소드





	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ScoreProcess prc = new ScoreProcess();

		do {
			System.out.println();
			System.out.println("---------성적 처리--------");
			System.out.println("1.성적 입력");
			System.out.println("2.성적 출력");
			System.out.println("3.이름 검색");
			System.out.println("종료는 -1을 누르세요");

			int no = sc.nextInt();
			if(no == -1) {
				System.out.println("프로그램을 종료합니다.");
			}

			switch(no) {
			case 1:
				prc.insert(); break;
			case 2:
			case 3:
			}
		}while (true);
	}

}
