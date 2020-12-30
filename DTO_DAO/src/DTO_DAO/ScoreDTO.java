package DTO_DAO;

public class ScoreDTO {
	// 멤버 선언
	private int kor, eng, math, total;
	private float avg;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	public int getTotal() {
		return total = getKor()+getEng()+getMath();
	}
	
	public float getAvg() {
		return avg = getTotal() / 3;
	}


}
