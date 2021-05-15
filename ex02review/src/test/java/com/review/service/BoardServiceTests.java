package com.review.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardServiceTests {
	@Setter(onMethod_=@Autowired)
	private BoardService service;
	
//	@Test
//	public void testExist() {
//		assertNotNull(service);
//		log.info(service);
//	}
	
//	@Test
//	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
//	}
	
//	@Test
//	public void testRegister() {
//		BoardVO board = new BoardVO();
//		board.setContent("Service Test content");
//		board.setTitle("Service Test title");
//		board.setWriter("stest123");
//		service.register(board);
//	}
	
//	@Test
//	public void testRegisterWithPK() {
//		BoardVO board = new BoardVO();
//		board.setContent("Service Test content");
//		board.setTitle("Service Test title");
//		board.setWriter("stest123");
//		service.registerWithPK(board);
//	}
	
//	@Test
//	public void testRead() {
//		log.info(service.read(10L));
//	}
	
//	@Test
//	public void testModify() {
//		BoardVO board = service.read(4L);
//		board.setTitle("매퍼 테스트 제목 수정");
//		board.setContent("매퍼 테스트 내용 수정");
//		log.info(service.modify(board));
//	}
	
//	@Test
//	public void testDelete() {
//		log.info(service.delete(24L));
//	}
	
	@Test
	public void testGetListPaging() {
		service.getListPaging(new Criteria(2, 5)).forEach(board -> log.info(board));
	}
}
