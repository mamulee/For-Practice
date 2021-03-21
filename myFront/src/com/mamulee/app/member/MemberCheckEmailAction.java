package com.mamulee.app.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamulee.action.Action;
import com.mamulee.action.ActionForward;
import com.mamulee.app.member.dao.MemberDAO;

public class MemberCheckEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");

		String email = req.getParameter("email");
		MemberDAO m_dao = new MemberDAO();
		PrintWriter out = resp.getWriter();

		resp.setContentType("text/html;charset=utf-8");
		if(m_dao.checkEmail(email)) {
			out.println("not-ok");
		}else {
			out.print("ok");
		}
		out.close();

		return null;
	}

}
