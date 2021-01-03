package exception;

import java.util.regex.Pattern;

public class SignUpException {

	// 전화번호 확인
	public void telCheck(String tel) throws MyException {
		boolean check = Pattern.matches(
				//"^[0-9]*$",
				"^\\d{11}",
				tel);
		if (!check) {
			throw new MyException("11자리 숫자만 입력 가능합니다.");
		}
	}
	// 이메일 확인
	public void emailCheck(String email) throws MyException {
		boolean check = Pattern.matches(
				"^\\w+@\\w+\\.\\w+(\\.\\w+)?", 
				email);
		if (!check) {
			throw new MyException (
					"이메일 형식이 맞지 않습니다.\n"
					+ "(예시) xxx@xxx.xxx 혹은 xxx@xxx.xxx.xx)"
					);
		}
	}
	// 비밀번호 확인
	public void pwCheck(String pw1, String pw2) throws MyException {
		int cnt1 = 0;
		int cnt2 = 0;
		
		for(int i=0; i<pw1.length(); i++) {
			char ch = pw1.charAt(i);
			if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
				cnt1++;
			} else if(ch >= '0' && ch <= '9') {
				cnt2++;
			}
		}
		
		if(cnt1 == 0 || cnt2 == 0) {
			throw new MyException (
			"비밀번호는 영문자와 숫자를 포함해야 합니다."		
					);
		}
		
		if(!pw1.equals(pw2)) {
			throw new MyException (
					"비밀번호가 일치하지 않습니다."
					);
		}
	}
}
