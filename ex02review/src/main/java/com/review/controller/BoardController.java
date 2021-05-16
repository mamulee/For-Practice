package com.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.review.domain.BoardVO;
import com.review.domain.Criteria;
import com.review.domain.PageDTO;
import com.review.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {
	private BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list...................");
//		model.addAttribute("list", service.getList());
//	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list w/ paging..................."+cri);
		model.addAttribute("list", service.getListPaging(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@GetMapping("/register")
	public void register(@ModelAttribute("cri") Criteria cri) {}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register..................." + board);
		service.register(board);
		rttr.addAttribute("result", board.getBno());
		return "redirect:/board/list";
	}
	
	@GetMapping({"/read", "modify"})
	public void read(Long bno, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("read..................."+bno);
		model.addAttribute("board", service.read(bno));
	}
	
	@PostMapping("/board/modify")
	public String modify(@ModelAttribute("board") BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("modify..................."+board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/read?bno="+board.getBno();
	}
	
	@GetMapping("/board/delete")
	public String delete(Long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("delete..................."+bno);
		if(service.delete(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}
}
