                 
CREATE TABLE MEMBER(
        tel VARCHAR2(11) PRIMARY KEY,
        email VARCHAR(100) NOT NULL,
        NAME VARCHAR2(20) NOT NULL,
        pw VARCHAR2(20) NOT NULL);
        
COMMIT;

select * from member;