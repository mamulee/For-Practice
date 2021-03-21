package com.mamulee.app.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mamulee.action.Action;
import com.mamulee.action.ActionForward;
import com.mamulee.app.member.dao.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		
		MemberDAO m_dao = new MemberDAO();
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		
		String id = req.getParameter("loginId");
		String pw = req.getParameter("loginPw");
		
		if(m_dao.login(id, pw)) {
			session.setAttribute("sessionId", id);
			forward.setRedirect(true);
			forward.setPath(req.getContextPath()+"/home.jsp");
		}else {
			forward.setRedirect(false);
			forward.setPath("/member/MemberLogin.me?login=false");
		}
		
		return forward;
	}

}
