package com.review.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",	
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"	
})
@Log4j
public class BoardControllerTests {
	@Setter(onMethod_=@Autowired)
	private WebApplicationContext wac;
	
	private MockMvc mmvc;
	
	@Before
	public void setup() {
		// WebApplicationContext를 통해 ServletContext를 빌드한다
		this.mmvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
//	@Test
//	public void testList() throws Exception{
//		log.info(mmvc.perform(MockMvcRequestBuilders.get("/board/list"))
//					.andReturn()
//					.getModelAndView()
//					.getModelMap());
//	}
	
//	@Test
//	public void testRegister() throws Exception{
//		log.info(mmvc.perform(MockMvcRequestBuilders.post("/board/register")
//								.param("title", "Controller test title")
//								.param("content", "Controller test content")
//								.param("writer", "ctest123"))
//				.andReturn().getModelAndView().getModelMap());
//	}
	
//	@Test
//	public void testRead() throws Exception{
//		log.info(mmvc.perform(MockMvcRequestBuilders.get("/board/read")
//				.param("bno", "10"))
//		.andReturn().getModelAndView().getModelMap());
//	}
	
//	@Test
//	public void testModify() throws Exception {
//		log.info(mmvc.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("bno", "7")
//				.param("title", "수정이")
//				.param("content", "수정"))
//		.andReturn().getFlashMap());
//	}
	
//	@Test
//	public void testDelete() throws Exception {
//		log.info(mmvc.perform(MockMvcRequestBuilders.get("/board/delete")
//					.param("bno", "23"))
//		.andReturn().getFlashMap());
//	}
	
	@Test
	public void testGetListPaging() throws Exception{
		log.info(mmvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "1")
				.param("amount", "5"))
		.andReturn().getModelAndView().getModelMap());
	}
}
