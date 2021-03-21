package com.mamulee.app.board.vo;
/*CREATE TABLE MYFRONT_BOARD(
	BOARDNUM NUMBER(10),
	BOARDTITLE VARCHAR2(1000),
	BOARDCONTENT VARCHAR2(3000),
	BOARDID VARCHAR2(100),
	BOARDDATE DATE,
	READCOUNT NUMBER(10),
	CONSTRAINT MYBOARD_PK PRIMARY KEY(BOARDNUM),
	CONSTRAINT MYBOARD_FK FOREIGN KEY(BOARDID) REFERENCES MYFRONT_MEMBER(MEMBERID)
);*/
public class BoardVO {
	private String boardNum;
	private String boardTitle;
	private String boardContent;
	private String boardId;
	private String boardDate;
	private int readCount;
	
	public BoardVO() {;}
	
	public String getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(String boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	
}
