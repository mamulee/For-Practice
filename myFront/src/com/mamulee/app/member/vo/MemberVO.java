package com.mamulee.app.member.vo;
/*CREATE TABLE MYFRONT_MEMBER(
		memberId VARCHAR2(300),
		memberPw VARCHAR2(300),
		memberName VARCHAR2(300),
		memberEmail VARCHAR2(200),
		CONSTRAINT MYFRONT_PK PRIMARY KEY(memberId)
	);*/
public class MemberVO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	
	public MemberVO() {;}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	@Override
		public String toString() {
			return memberId +" "+memberPw+" "+memberName+" "+memberEmail;
		}
}
