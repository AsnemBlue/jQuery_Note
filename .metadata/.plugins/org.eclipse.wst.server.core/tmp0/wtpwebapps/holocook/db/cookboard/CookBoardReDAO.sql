--댓글 목록 불러오기
SELECT * FROM(SELECT ROWNUM RN, A.* FROM(
    SELECT C.*, (SELECT MNICK FROM MEMBER WHERE MID=C.MID ) MNICK, 
    (SELECT MPHOTO FROM MEMBER WHERE MID=C.MID ) MPHOTO,
    (SELECT MBLACK FROM MEMBER WHERE MID=C.MID ) MBLACK
    FROM COOKBOARDRE C WHERE CBNO=450 ORDER BY CBRNO DESC
    ) A) WHERE RN BETWEEN 1 AND 5;
SELECT * FROM(SELECT ROWNUM RN, A.* FROM(
    SELECT C.*, (SELECT MNICK FROM MEMBER WHERE MID=C.MID ) MNICK, (SELECT MPHOTO FROM MEMBER WHERE MID=C.MID ) MPHOTO FROM COOKBOARDRE C WHERE CBNO=430 ORDER BY CBRNO DESC
    ) A) WHERE RN BETWEEN 1 AND 5;
--총 댓글 갯수
SELECT COUNT(*) FROM COOKBOARDRE WHERE CBNO=860;
--댓글 작성
INSERT INTO COOKBOARDRE (CBRNO,CBNO, MID, CBRCONTENT, CBRIP) VALUES(COOKBOARDRE_SEQ.NEXTVAL, 860,'eee5', 'wow', '2.2.2.2');
--댓글 수정
UPDATE COOKBOARDRE SET CBRCONTENT='FIXIT', CBRIP='3.3.3.3' WHERE CBRNO=4 AND CBNO=3;
--댓글 삭제
DELETE FROM COOKBOARDRE WHERE CBRNO=4 AND CBNO=3;
--COOKBOARDDETAIL 삭제
DELETE FROM COOKBOARDRE WHERE CBNO=3;

SELECT TO_CHAR(CBRRDATE, 'YYYY-MM-DD HH24:MI:SS') FROM COOKBOARDRE WHERE CBRNO=15;
SELECT * FROM COOKBOARDRE WHERE CBNO=450;
COMMIT;