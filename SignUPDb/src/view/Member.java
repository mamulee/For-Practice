package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import controller.MemberDAO;
import db.SQLConnection;
import exception.MyException;
import exception.SignUpException;
import model.MemberDTO;

public class Member {

	Scanner sc = new Scanner(System.in);
	MemberDAO dao = new MemberDAO();
	SignUpException sue = new SignUpException();

	// 메뉴
	public int display() {
		System.out.println("==========메뉴==========");
		System.out.println("1.회원가입 2.전체 회원 조회 3. 종료");
		System.out.print("선택 ==> ");
		int choice = sc.nextInt();
		
		return choice;
	}
	
	public void view() throws MyException {
		boolean flag = true;
		
		while(flag) {
			switch(display()) {
			case 1:
				insert();
				break;
			case 2:
				selectAll();
				break;
			case 3:
				System.out.println("프로그램 종료.");
				SQLConnection.close();
				flag = false;
				break;
			}
		}
	}
	
	// 회원가입
	public void insert() throws MyException {
		boolean tel = true;
		boolean email = true;
		boolean name = true;
		boolean pw = true;
		String pw2;

		System.out.println("=======회원가입======");

		try {
			MemberDTO dto = new MemberDTO();
			
			do {
				try {
					System.out.print("전화번호: ");
					dto.setTel(sc.next());
					sue.telCheck(dto.getTel());
					
					tel = false;
					
				} catch(MyException e) {
					System.out.println(e.getMessage());
				}
			}while(tel);
			
			do {
				try {
					System.out.print("이메일: ");
					dto.setEmail(sc.next());
					sue.emailCheck(dto.getEmail());
					
					email = false;
					
				} catch(MyException e) {
					System.out.println(e.getMessage());
				}
			} while(email);
			
			
			do {
				System.out.print("이름: ");
				dto.setName(sc.next());
				
				name = false;
				
			}while(name);
			
			do {
				try {
					System.out.print("비밀번호: ");
					dto.setPw(sc.next());
					System.out.println("비밀번호 확인: ");
					pw2 = sc.next();
					
					sue.pwCheck(dto.getPw(), pw2);
					pw = false;
					
				} catch (MyException e) {
					System.out.println(e.getMessage());
				}
			}while(pw);
			
			int result = dao.insertData(dto);
			if(result != 0) {
				System.out.println("회원가입 성공!");
				System.out.println("=====회원가입 확인=====");
				System.out.println(dto);
			}else {
				System.out.println("회원가입 실패,,,");
			}
			
			
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
	} //insert()

	// 전체 회원 출력
	public void selectAll() {
		ArrayList<MemberDTO> lists = dao.getList();
		Iterator<MemberDTO> itr = lists.iterator();
		int i = 1;
		while(itr.hasNext()) {
			MemberDTO dto = itr.next();
			System.out.println(i+"번째 회원");
			System.out.println(dto);
			i++;
		}
	}


}
