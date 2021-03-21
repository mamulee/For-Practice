package com.mamulee.app.member.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mamulee.app.member.vo.MemberVO;
import com.mamulee.mybatis.config.SqlMapConfig;

public class MemberDAO {
	private static final int KEY = 3;
	
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession session;
	
	public MemberDAO() {
		session = sessionf.openSession(true);
	}
	
	public boolean signUp(MemberVO member) {
		member.setMemberPw(encrypt(member.getMemberPw()));
		return session.insert("Member.signUp", member) == 1;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * true : 중복 <br>false : 사용 가능
	 */
	public boolean checkId(String id) {
		return (Integer) session.selectOne("Member.checkId", id) == 1;
	}
	
	public boolean checkEmail(String email) {
		return (Integer) session.selectOne("Member.checkEmail", email) == 1;
	}
	
	public boolean login(String id, String pw) {
		HashMap<String, String> login = new HashMap<>();
		login.put("memberId", id);
		login.put("memberPw", encrypt(pw));
		return (Integer) session.selectOne("Member.login", login) == 1;
	}
	
	public boolean findId(String email) {
		return (Integer) session.selectOne("Member.findId", email) == 1;
	}
	
	public String returnId(String email) {
		return session.selectOne("Member.returnId", email);
	}
	
	public boolean findPw(String id, String email) {
		HashMap<String, String> find = new HashMap<>();
		find.put("memberId", id);
		find.put("memberEmail", email);
		return (Integer) session.selectOne("Member.findPw", find) == 1;
	}
	
	public boolean changePw(String id, String pw) {
		HashMap<String, String> change = new HashMap<>();
		change.put("memberId", id);
		change.put("memberPw", pw);
		return session.update("Member.changePw", change) == 1;
	}
	
	
	
	
	
	
	public String encrypt(String pw) {
		String en_pw = "";
		for (int i = 0; i < pw.length(); i++) {
			en_pw += (char)(pw.charAt(i) * KEY);
		}
		return en_pw;
	}
	
	public String decrypt(String en_pw) {
		String de_pw = "";
		for (int i = 0; i < en_pw.length(); i++) {
			de_pw += (char)(en_pw.charAt(i) / KEY);
		}
		return de_pw;
	}
}
