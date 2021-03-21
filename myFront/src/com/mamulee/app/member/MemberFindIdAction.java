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
import com.mamulee.util.SHA256;

public class MemberFindIdAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberDAO m_dao = new MemberDAO();
		String email = req.getParameter("memberEmail");
		String memberId = null;
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		
		if(!m_dao.findId(email)) {
			out.println("not-ok");
		}else {
			String encryptId = "";
			memberId = m_dao.returnId(email);
			for(int i=0; i<memberId.length(); i++) {
				if(i<memberId.length()-3) {
					encryptId += memberId.charAt(i);					
				}else {
					encryptId += "*";
				}
			}
			String from = "mamulee@mamulee.com";
			String to = req.getParameter("memberEmail");
			String subject = "아이디를 찾기 위한 메일입니다.";
			String content = "가입한 아이디 : " + encryptId;
			
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
