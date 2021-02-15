

--유저정보 테이블 생성
CREATE TABLE kakaouser (
    user_num NUMBER(4) PRIMARY KEY,
    phonenum VARCHAR2(11) NOT NULL UNIQUE,
    NAME VARCHAR2(20) NOT NULL,
    PASSWORD VARCHAR2(20) NOT NULL
    );
--유저정보테이블 번호부여 시퀀스
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;
--확인용 유저 정보 추가
INSERT INTO kakaouser VALUES (user_seq.NEXTVAL, '01012341234', '고희광', '1234');
INSERT INTO kakaouser VALUES (user_seq.NEXTVAL, '01011111111', '김민재', '1234');
INSERT INTO kakaouser VALUES (user_seq.NEXTVAL, '01022222222', '홍아현', '1234');
--kakaouser테이블 확인
SELECT * FROM KAKAOUSER ;



--채팅룸 테이블 생성
CREATE TABLE chatroom (
   room_num NUMBER(4) PRIMARY KEY,
   user1_num number(4) NOT NULL,
   user2_num number(4) NOT NULL,
   CONSTRAINT charromm_user_UK UNIQUE (user1_num, user2_num), --유저 둘이 들어온 방은 유일하다
   CONSTRAINT chatroom_user1_num_FK FOREIGN KEY (user1_num) REFERENCES kakaouser(user_num),
   CONSTRAINT chatroom_user2_num_FK FOREIGN KEY (user2_num) REFERENCES kakaouser(user_num)
);
--채팅룸 방번호시퀀스 생성
CREATE SEQUENCE chatroom_seq START WITH 1 INCREMENT BY 1;
SELECT * FROM chatroom;

--메세지 내역 저장 테이블
CREATE TABLE chatMessage(
   message_num number(20) PRIMARY KEY, --메세지번호
   message varchar2(50) NOT NULL, --메세지 내용
   message_time DATE DEFAULT sysdate, --메세지 보낸 시간
   user_num number(4) NOT NULL, --보낸사람 번호
   room_num number(4) NOT NULL, --방번호
   CONSTRAINT chatMessage_user_num_FK FOREIGN KEY (user_num) REFERENCES kakaouser(USER_NUM),
   CONSTRAINT chatMessage_room_num_FK FOREIGN KEY (room_num) REFERENCES chatroom(ROOM_NUM)
);
--메세지 내역 저장 테이블
CREATE SEQUENCE chatMessage_seq START WITH 1 INCREMENT BY 1;

SELECT * FROM chatMessage;

-- 이게 쓰는 거야!!!!!!!!
SELECT DISTINCT cm1.room_num, cr.user1_num, cr.user2_num, cm2.message, latest FROM (
    SELECT room_num, max(message_time) latest 
    FROM chatmessage GROUP BY room_num
    ) cm1, chatroom cr, chatmessage cm2
    WHERE cm1.room_num = cr.room_num AND latest = cm2.message_time
    ORDER BY latest desc;

-----------------------------------------------------------  
SELECT DISTINCT cm.room_num, cr.user1_num, cr.user2_num 
    FROM chatmessage cm JOIN chatroom cr
    ON cm.room_num = cr.room_num;

SELECT DISTINCT room_num FROM (
    SELECT room_num, max(message_time) latest 
    FROM chatmessage 
    GROUP BY room_num
    )
    ORDER BY latest desc;

SELECT * FROM chatroom;

SELECT SYSDATE, CURRENT_TIMESTAMP FROM dual;

SELECT to_char(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MI') FROM dual;

CREATE TABLE user_data(
   user_num NUMBER PRIMARY KEY,
   user_status varchar2(60) DEFAULT '',
   user_image blob DEFAULT empty_blob(),
   CONSTRAINT user_data_FK FOREIGN KEY (user_num) REFERENCES kakaouser(user_num)
);

COMMIT;

SELECT * FROM user_data;

DELETE FROM chatMessage;
DELETE FROM chatRoom;
DELETE FROM user_data;
DELETE FROM kakaouser;
COMMIT;

ALTER TABLE chatroom add (lastLogOn_user1 DATE);
ALTER TABLE chatroom add (lastLogOn_user2 DATE);

UPDATE chatroom SET lastlogon_user1 = sysdate ;
UPDATE chatroom SET lastlogon_user2 = sysdate ;
COMMIT;