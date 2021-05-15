package com.review.service;

import java.util.List;

import com.review.domain.BoardVO;
import com.review.domain.Criteria;

public interface BoardService {
	public List<BoardVO> getList();
	
	public List<BoardVO> getListPaging(Criteria cri);
	
	public void register(BoardVO board);
	
	public void registerWithPK(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean delete(Long bno);
	
	public int getTotal(Criteria cri);
}
