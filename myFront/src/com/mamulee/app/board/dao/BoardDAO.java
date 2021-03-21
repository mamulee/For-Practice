package com.mamulee.app.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mamulee.app.board.vo.BoardVO;
import com.mamulee.mybatis.config.SqlMapConfig;

public class BoardDAO {
	private static final int KEY = 3;
	
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession session;
	
	public BoardDAO() {
		session = sessionf.openSession(true);
	}
	
	// 게시글 등록
	public boolean insertBoard(BoardVO b_vo) {
		return session.insert("Board.insertBoard", b_vo) == 1;
	}
	
	public int getBoardCnt() {
		return session.selectOne("Board.getBoardCnt");
	}
	
	public List<BoardVO> getBoardList(int startRow, int endRow){
		HashMap<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		return session.selectList("Board.getBoardList", pageMap);
	}
	
	public BoardVO getBoard(int boardNum) {
		return session.selectOne("Board.getBoard", boardNum);
	}
	
	public void updateReadCnt(int boardNum) {
		session.update("Board.updateReadCnt", boardNum);
	}
}