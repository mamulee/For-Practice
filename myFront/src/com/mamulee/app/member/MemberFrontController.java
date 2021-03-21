package com.mamulee.app.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamulee.action.ActionForward;
import com.mamulee.util.EmailSendAction;

public class MemberFrontController extends HttpServlet {
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
		
		if(command.equals("/Home.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/home.jsp");
		}else if(command.equals("/member/MemberSignUp.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("signUp.jsp");
		}else if(command.equals("/member/MemberCheckIdAction.me")){
			try {
				forward = new MemberCheckIdAction().execute(req, resp);
			} catch (Exception e) {;}
		}else if(command.equals("/member/MemberCheckEmailAction.me")){
			try {
				forward = new MemberCheckEmailAction().execute(req, resp);
			} catch (Exception e) {;}
		}else if(command.equals("/util/EmailSendAction.me")){
			try {
				forward = new EmailSendAction().execute(req, resp);
			} catch (Exception e) {;}
		}else if(command.equals("/member/MemberSignUpAction.me")){
			try {
				forward = new MemberSignUpAction().execute(req, resp);
			} catch (Exception e) {;}
		}else if(command.equals("/member/MemberLogin.me")){
			String login = req.getParameter("login");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("login.jsp"+(login != null ? login.equals("false")? "?login=false" : "?login=new" : ""));
		}else if(command.equals("/member/MemberLoginAction.me")){
			try {
				forward = new MemberLoginAction().execute(req, resp);
			} catch (Exception e) {;}
		}else if(command.equals("/member/MemberForget.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("findIdPw.jsp");
		}else if(command.equals("/member/MemberFindIdAction.me")){
			try {
				forward = new MemberFindIdAction().execute(req, resp);
			} catch (Exception e) {;}
		}else if(command.equals("/member/MemberFindPwAction.me")){
			try {
				forward = new MemberFindPwAction().execute(req, resp);
			} catch (Exception e) {;}
		}else if(command.equals("/member/MemberChangePw.me")) {
			String memberId = req.getParameter("memberId");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("changePw.jsp?memberId"+memberId);
		}else if(command.equals("/member/MemberChangePwAction.me")){
			try {
				forward = new MemberChangePwAction().execute(req, resp);
			} catch (Exception e) {;}
		}else if(command.equals("/member/MemberLogout.me")){
			try {
				forward = new MemberLogoutAction().execute(req, resp);
			} catch (Exception e) {;}
		}else {
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
