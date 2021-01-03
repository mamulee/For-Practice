package view;

import java.util.Scanner;

import exception.MyException;

public class MemberMain {

	public static void main(String[] args) throws MyException {

		Scanner sc = new Scanner(System.in);
		
		Member m = new Member();
		
		m.view();
		
	}

}
