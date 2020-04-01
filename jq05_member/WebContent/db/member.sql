DROP TABLE MEMBER;
CREATE TABLE MEMBER(
    ID VARCHAR2(30) NOT NULL,
    PW VARCHAR2(30) NOT NULL,
    NAME VARCHAR2(100) NOT NULL,
    BIRTH DATE,
    GENDER VARCHAR2(3) NOT NULL,
    EMAIL VARCHAR2(100) NOT NULL,
    PHONE VARCHAR2(100),
    ADDRESS VARCHAR2(100),
    PRIMARY KEY(ID)
);
SELECT * FROM MEMBER;
COMMIT;
ROLLBACK;

--�����ϱ�
INSERT INTO MEMBER VALUES ('AAA','111','PARK','87/05/24','M','JEENPARK@NAVER.COM','010-2017-7610','����� ���α�');
INSERT INTO MEMBER VALUES ('BBB','222','KIM','94/04/24','F','KIMKIM@NAVER.COM','010-1112-2222','����� ��������');
INSERT INTO MEMBER VALUES ('CCC','333','CHOK','94/04/24','F','A@A.COM','010-1111-1111','����� ��������');
--ID �ߺ� CHK
SELECT COUNT(*) FROM MEMBER WHERE ID='AAA';
--MAIL �ߺ� CHK
SELECT COUNT(*) FROM MEMBER WHERE EMAIL='JEENPARK@NAVER.COM';