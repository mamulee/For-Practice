package com.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.review.domain.BoardVO;
import com.review.domain.Criteria;
import com.review.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImple implements BoardService {

	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("ServiceImple getList().....................");
		return mapper.selectList();
	}

	@Override
	public void register(BoardVO board) {
		log.info("ServiceImple register()...............");
		mapper.insert(board);
	}

	@Override
	public void registerWithPK(BoardVO board) {
		log.info("ServiceImple registerWithPK()...............");
		mapper.insertSelectPK(board);
	}

	@Override
	public BoardVO read(Long bno) {
		log.info("ServiceImple read()...............");
		return mapper.selectBoard(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("ServiceImple modify()...............");
		return mapper.updateBoard(board) == 1;
	}

	@Override
	public boolean delete(Long bno) {
		log.info("ServiceImple delete()...............");
		return mapper.deleteBoard(bno) == 1;
	}

	@Override
	public List<BoardVO> getListPaging(Criteria cri) {
		log.info("ServiceImple getListPaging()...............");
		return mapper.selectListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("ServiceImple getTotal()...............");
		return mapper.selectCount(cri);
	}

}
