SELECT * FROM LIKEHISTORY;

--좋아요 CHK
SELECT COUNT(*) FROM LIKEHISTORY WHERE CBNO=430 AND MID='ccc1';
--좋아요 추가
INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,428,'ccc1');
--좋아요 제거
DELETE FROM LIKEHISTORY WHERE CBNO=1 AND MID='aaa1';
DELETE FROM LIKEHISTORY WHERE CBNO=450;

delete from likehistory;
INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,10,'BBBB');
INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,3,'BBBB');
INSERT INTO LIKEHISTORY VALUES (LIKEHISTORY_SEQ.NEXTVAL,4,'BBBB');
COMMIT;
ROLLBACK;