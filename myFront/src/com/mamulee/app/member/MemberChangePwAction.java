package com.mamulee.app.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamulee.action.Action;
import com.mamulee.action.ActionForward;
import com.mamulee.app.member.dao.MemberDAO;

public class MemberChangePwAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String memberId = req.getParameter("memberId");
		String newPw = req.getParameter("newPw");
		
		MemberDAO m_dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		
		if(m_dao.changePw(memberId, newPw)) {
			forward.setRedirect(false);
			forward.setPath("/member/MemberLogin.me?login=new");
		}else {
			out.print("<script>alert('서버가 불안정합니다. 잠시 후 다시 시도해 주세요.');</script>");
			out.close();
		}
		
		
		return forward;
	}

}
