package com.mamulee.app.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamulee.action.ActionForward;
import com.mamulee.util.EmailSendAction;

public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		
		ActionForward forward = new ActionForward();
		
		switch(command) {
		case "/board/BoardList.bo":
			try {
				req.setAttribute("page", req.getParameter("page"));
				forward = new BoardListAction().execute(req, resp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case "/board/BoardWrite.bo":
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/boardWrite.jsp");
			break;
		case "/board/BoardWriteAction.bo":
			try {
				forward = new BoardWriteAction().execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/error/404.jsp");
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
			}
		}
	}
}
