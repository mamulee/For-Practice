package com.mamulee.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamulee.action.Action;
import com.mamulee.action.ActionForward;
import com.mamulee.app.board.dao.BoardDAO;
import com.mamulee.app.board.vo.BoardVO;

public class BoardViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForward forward = null;
		BoardDAO b_dao = new BoardDAO();
		
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		int page = Integer.parseInt(req.getParameter("page"));
		
		BoardVO b_vo = b_dao.getBoard(boardNum);
		
		if(b_vo != null) {
			b_dao.updateReadCnt(boardNum);
			req.setAttribute("b_vo", b_vo);
			req.setAttribute("page", page);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/boardView.jsp");
		}
		
		return forward;
	}

}
