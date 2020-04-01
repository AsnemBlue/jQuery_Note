DROP TABLE CART;
DROP TABLE ORDERDETAIL;
DROP TABLE PRODUCT;
CREATE TABLE PRODUCT(
    PNO NUMBER(4),
    PNAME VARCHAR2(100) NOT NULL,
    PSTOCK NUMBER(4) NOT NULL,
    PRIMARY KEY(PNO)
);
INSERT INTO PRODUCT VALUES (1,'공책',3);
INSERT INTO PRODUCT VALUES (2,'볼펜',10);
INSERT INTO PRODUCT VALUES (3,'지우개',10);
SELECT * FROM PRODUCT WHERE PNAME='공책';
SELECT * FROM PRODUCT;
select c.table_name, c.constraint_name, c.constraint_type, s.column_name

from user_constraints c, user_cons_columns s

where c.constraint_name = s.constraint_name

and c.table_name in ('PRODUCT')

order by c.table_name;
commit;
