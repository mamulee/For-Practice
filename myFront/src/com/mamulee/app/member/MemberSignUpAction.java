package com.mamulee.app.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamulee.action.Action;
import com.mamulee.action.ActionForward;
import com.mamulee.app.member.dao.MemberDAO;
import com.mamulee.app.member.vo.MemberVO;

public class MemberSignUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		
		MemberDAO m_dao = new MemberDAO();
		MemberVO m_vo = new MemberVO();
		
		ActionForward forward = null;
		
		m_vo.setMemberId(req.getParameter("memberId"));
		m_vo.setMemberPw(req.getParameter("memberPw"));
		m_vo.setMemberName(req.getParameter("memberName"));
		m_vo.setMemberEmail(req.getParameter("memberEmail"));
		
		if(m_dao.signUp(m_vo)) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/member/MemberLogin.me");
		}else {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html;charset=utf-8");
			out.print("<script>alert('서버가 불안정합니다. 잠시 후 다시 시도해 주세요.');</script>");
			out.close();
		}
		
		
		return forward;
	}

}
