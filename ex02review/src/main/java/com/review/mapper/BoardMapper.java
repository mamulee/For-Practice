package com.review.mapper;

import java.util.List;

import com.review.domain.BoardVO;
import com.review.domain.Criteria;

public interface BoardMapper {
	public List<BoardVO> selectList();
	
	public List<BoardVO> selectListWithPaging(Criteria cri);
	
	public int selectCount(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectPK(BoardVO board);
	
	public BoardVO selectBoard(Long bno);
	
	public int updateBoard(BoardVO board);
	
	public int deleteBoard(Long bno);
}
