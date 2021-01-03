package model;

public class MemberDTO {
	private String tel;
	private String email;
	private String name;
	private String pw;
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	@Override
	public String toString() {
		String str = String.format(
				"전화번호: %s\n이메일: %s\n 이름: %s\n 비밀번호: %s\n",
				tel, email, name, pw);
		return str;
	}
}
