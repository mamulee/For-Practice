package com.mamulee.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamulee.action.Action;
import com.mamulee.action.ActionForward;
import com.mamulee.app.board.dao.BoardDAO;
import com.mamulee.app.board.vo.BoardVO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		
		ActionForward forward = null;
		BoardDAO b_dao = new BoardDAO();
		BoardVO b_vo = new BoardVO();
		
		b_vo.setBoardTitle(req.getParameter("boardTitle"));
		b_vo.setBoardId(req.getParameter("boardId"));
		b_vo.setBoardContent(req.getParameter("boardContent"));
		
		if(b_dao.insertBoard(b_vo)) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(req.getContextPath()+"/board/BoardList.bo");
		}
		
		return forward;
	}

}
