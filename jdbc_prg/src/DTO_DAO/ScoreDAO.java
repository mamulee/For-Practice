package DTO_DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// 데이터 엑세스를 위한 전용 클래스
// 컬렉션에 데이터를 입출력 하는 클래스

public class ScoreDAO {

	private ArrayList<ScoreDTO> lists = new ArrayList<ScoreDTO>();
	
	// 등록된 학생 수
	public int stu_cnt() {
		return this.lists.size();
	}
	
	// 학생을 lists에 insert
	public void setStudent(ScoreDTO dto) {
		lists.add(dto);
	}
	
	// 학생들 정렬, 이름 기준
	public void sort() {
		Comparator <ScoreDTO> cmp = new Comparator<ScoreDTO>() {

			@Override
			public int compare(ScoreDTO sdto1, ScoreDTO sdto2) {
				// sdto1 우선 -1, 같으면 0, sdto2 우선 1 리턴
				return sdto1.getName().compareTo(sdto2.getName());
			}
		};
		
		// (정렬하는 리스트, 정렬 기준 Comparator)
		Collections.sort(lists, cmp);
		
	}
	
	// 학생들 출력
	public ArrayList<ScoreDTO> getLists() {
		return lists;
	}
	
	// 검색된 학생 출력
	public ArrayList<ScoreDTO> getLists(String name) {
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		for(ScoreDTO sdto : lists) {
			if(sdto.getName().equals(name)) {
				result.add(sdto);
			}
		} // for문
		return result;
	}
	
	
	
	
	
} // ScoreDAO class
