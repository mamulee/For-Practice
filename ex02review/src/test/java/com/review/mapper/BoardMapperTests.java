package com.review.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.review.domain.BoardVO;
import com.review.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
//	@Test
//	public void testSelectList() {
//		log.info(mapper.selectList());
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO();
//		board.setContent("Mapper Test content");
//		board.setTitle("Mapper Test title");
//		board.setWriter("mtest123");
//		mapper.insert(board);
//	}
	
//	@Test
//	public void testInsertSelectPK() {
//		BoardVO board = new BoardVO();
//		board.setContent("Mapper Test content");
//		board.setTitle("Mapper Test title");
//		board.setWriter("mtest123");
//		mapper.insertSelectPK(board);
//	}
	
//	@Test
//	public void testSelectBoard() {
//		log.info(mapper.selectBoard(10L));
//	}
	
//	@Test
//	public void testUpdateBoard() {
//		BoardVO board = mapper.selectBoard(3L);
//		board.setTitle("매퍼 테스트 제목");
//		board.setContent("매퍼 테스트 내용");
//		log.info(mapper.updateBoard(board));
//	}
	
//	@Test
//	public void testDeleteBoard() {
//		log.info(mapper.deleteBoard(22L));
//	}
	
//	@Test
//	public void testSelectListWithPaging() {
//		mapper.selectListWithPaging(new Criteria()).forEach(board -> log.info(board));
//	}
	
}
