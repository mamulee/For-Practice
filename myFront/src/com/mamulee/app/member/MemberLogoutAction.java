package com.mamulee.app.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mamulee.action.Action;
import com.mamulee.action.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		
		session.invalidate();
		
		forward.setRedirect(true);
		forward.setPath(req.getContextPath()+"/Home.me");
		
		return forward;
	}

}
