DROP TABLE MVC_MEMBER;
CREATE TABLE MVC_MEMBER(
    mId VARCHAR2(30) PRIMARY KEY,
    mPw VARCHAR2(30) NOT NULL,
    mName VARCHAR2(30) NOT NULL,
    mEmail VARCHAR2(30),
    mPhoto VARCHAR2(30) DEFAULT 'NOIMG.JPG' NOT NULL,
    mBirth DATE,
    mAddress VARCHAR2(300),
    mRdate  DATE DEFAULT SYSDATE
);
-- mId �ߺ�üũ
SELECT * FROM MVC_MEMBER WHERE mId='aaa';
-- ȸ������(mID, mPw, mNAME / mEMAIL, mPHOTO, mBIRTH, mADDRESS)
INSERT INTO MVC_MEMBER (mID, mPw, mName, mEmail, mPhoto, mBirth, mAddress)
    VALUES ('aaa','1','ȫ�浿','hong@naver.com','NOIMG.JPG','1990/12/12','����');
-- �α��� (mID, mPW)
SELECT * FROM MVC_MEMBER WHERE mID='aaa' and mPW='1';
--���ǿ� �ֱ� ���� mId�� member dto��������
SELECT * FROM MVC_MEMBER WHERE mId='aaa';
--ȸ����������(mPw, mNAME / mEMAIL, mPHOTO, mBIRTH, mADDRESS ���� ����)
UPDATE MVC_MEMBER SET mPw = '111',
                    mName = 'HONG',
                    mEmail = 'yi@naver.com',
                    mPhoto = 'noimg.jpg',
                    mBirth = '1991/12/12',
                    mAddress = '����'
        WHERE mId='aaa';
-- ȸ�����(startRow ~ endRow)
SELECT * FROM MVC_MEMBER ORDER BY mRDATE DESC;
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
                        (SELECT * FROM MVC_MEMBER ORDER BY mRDATE DESC) A)
        WHERE RN BETWEEN 1 AND 10;
-- ������ ��ü ȸ�� ���
SELECT COUNT(*) cnt FROM MVC_MEMBER;
SELECT * FROM MVC_MEMBER;
commit;