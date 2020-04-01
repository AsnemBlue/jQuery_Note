DROP TABLE FILEBOARD;
DROP SEQUENCE FILEBOARD_SEQ;
CREATE SEQUENCE FILEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE FILEBOARD(
    fId NUMBER(6) PRIMARY KEY,
    mId VARCHAR2(30) REFERENCES MVC_MEMBER(MID),
    fTitle VARCHAR2(100) NOT NULL,
    fContent VARCHAR2(1000),
    fFileName VARCHAR2(30),
    fRdate    DATE DEFAULT SYSDATE,
    fHit     NUMBER(6) DEFAULT 0,
    fGroup   NUMBER(6) NOT NULL,
    fStep    NUMBER(3) NOT NULL,
    fIndent  NUMBER(3) NOT NULL,
    fIp      VARCHAR2(20) NOT NULL);

-- ���̵�����(����)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME,  
        FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'AAA','title','content',null, 
        FILEBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
SELECT * FROM FILEBOARD;
-- ���̵�����(���� 1���ۿ� ���� ù��° �亯��)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME,
        FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'AAA','reply','content', null,
        1, 1, 1, '192.168.10.151');
select * from fileboard order by fgroup desc, fstep;
-- DAO�� ���� sql
-- �۸��(startRow���� endRow����)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT F.*, MNAME FROM FILEBOARD F, MVC_MEMBER M WHERE F.MID=M.MID ORDER BY FGROUP desc, FSTEP) A) WHERE RN BETWEEN 1 AND 2;
-- �۰���
SELECT COUNT(*) FROM FILEBOARD;
-- �۾���(����)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME,  
        FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'AAA','title','content',null, 
        FILEBOARD_SEQ.CURRVAL, 0, 0, '192.168.10.151');
-- FHit �ϳ� �ø���(1���� ��ȸ�� �ϳ� �ø���)
UPDATE FILEBOARD SET FHIT = FHIT+1 WHERE FID = 3;
-- bId�� �� dto����
SELECT F.*, MNAME FROM FILEBOARD F, MVC_MEMBER M WHERE F.MID=M.MID AND FID=3;
-- �� �����ϱ�(FId, FTitle, FContent, FILENAME,  FIp, FDATE)
UPDATE FILEBOARD SET FTITLE='UPDATETITLE', FCONTENT='this is updated', FFILENAME=NULL, FIp='0.0.0.0'  WHERE FID=3;
-- �� �����ϱ�(bId�� �����ϱ�)
DELETE FROM FILEBOARD WHERE FID=4;
--    �亯�� �߰��� STEP a ����
UPDATE FILEBOARD SET FSTEP=FSTEP+1, FINDENT=FINDENT+1 WHERE FSTEP>0 AND fgroup=1;
-- �亯�� ����
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME,
        FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'AAA','reply','content', null,
        1, 1, 1, '192.168.10.151');
commit;
ROLLBACK;
----------------------------------------------------------------------
--                          admin table                             --
----------------------------------------------------------------------
DROP TABLE ADMIN;
CREATE TABLE ADMIN(
    aId VARCHAR2(30) PRIMARY KEY,
    aPw VARCHAR2(30) NOT NULL,
    aName VARCHAR2(30) NOT NULL);
-- dummy data
INSERT INTO ADMIN VALUES ('admin','111','�����');
-- DAO�� ���� sql
-- admin loginCheck
SELECT * FROM ADMIN WHERE AID = 'admin' AND APW='111';
-- admin aid�� dto ��������
SELECT * FROM ADMIN WHERE AID = 'admin';
COMMIT;