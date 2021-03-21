package com.mamulee.app.member;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mamulee.action.Action;
import com.mamulee.action.ActionForward;
import com.mamulee.app.member.dao.MemberDAO;
import com.mamulee.util.Gmail;

public class MemberFindPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		MemberDAO m_dao = new MemberDAO();
		String id = req.getParameter("memberId");
		String email = req.getParameter("memberEmail");
		System.out.println(id+" "+email);
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		
		if(!m_dao.findPw(id, email)) {
			out.println("not-ok");
		}else {
			String host = "http://localhost:8081/myFront/";
			String from = "mamulee@mamulee.com";
			String to = email;
			String subject = "[MAMULEE] 비밀번호를 찾기 위한 메일입니다.";
			String content = "다음 링크에 접속하여 비밀번호 변경을 진행하십시오." +
					"<a href='" + host + "member/MemberChangePw.me?memberId=" + id + "'>비밀번호 변경하러 가기</a>";
			
			// SMTP에 접속하기 위한 정보를 기입합니다.
			Properties p = new Properties();
			p.put("mail.smtp.user", from);
			p.put("mail.smtp.host", "smtp.googlemail.com");
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");
			
			try{
				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(p, auth);
				ses.setDebug(true);
				MimeMessage msg = new MimeMessage(ses); 
				msg.setSubject(subject);
				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr);
				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO, toAddr);
				msg.setContent(content, "text/html;charset=UTF-8");
				Transport.send(msg);
				
			} catch(Exception e){
				e.printStackTrace();
				out.println("<script>");
				out.println("alert('오류가 발생했습니다..');");
				out.println("history.back();");
				out.println("</script>");
				out.close();		
				
			}
			
			out.println("ok");
		}
		
		return null;
	}

}
