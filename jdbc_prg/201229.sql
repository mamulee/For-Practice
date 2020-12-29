-- Eclipse n02, n03
CREATE TABLE MEMBER (
    NO NUMBER(3) PRIMARY KEY, 
    NAME VARCHAR2(20) NOT NULL,
    hp VARCHAR2(15),
    addr VARCHAR2(50)
    );

SELECT * FROM TAB;

INSERT INTO MEMBER VALUES(1, '강호동', '010-111-1234', '서울 용산구 남영동');

SELECT * FROM MEMBER;

INSERT INTO MEMBER VALUES(2, '이경규', '010-111-1234', '서울 강남구 역삼동');
INSERT INTO MEMBER VALUES(3, '유재석', '010-111-1234', '인천 중구');
INSERT INTO MEMBER VALUES(4, '홍길동', '010-111-1234', '서울 마포구 성산동');

SELECT * FROM MEMBER;
commit;

SELECT COUNT(*) FROM MEMBER;

SELECT * FROM MEMBER;SELECT * FROM MEMBER;

update member set name = '고길동' where no = 8;

delete member where no = 8;

delete member;
SELECT * FROM MEMBER;SELECT * FROM MEMBER;

INSERT INTO MEMBER VALUES(1, '강길동', '11111', '서울');
INSERT INTO MEMBER VALUES(2, '강길동', '11111', '서울');
INSERT INTO MEMBER VALUES(3, '강길동', '11111', '서울');
commit;

SELECT * FROM MEMBER;SELECT * FROM MEMBER;

INSERT INTO MEMBER VALUES(21, '강길동', '11111', '서울');
INSERT INTO MEMBER VALUES(3, '강길동', '11111', '서울');
commit;
SELECT * FROM MEMBER;

SELECT * FROM MEMBER;

----------------------------------------------------------------------------------------------------
-- Eclipse n03 select
----------------------------------------------------------------------------------------------------
commit;
