INSERT INTO
    ACCOUNT_TYPE (ID,TYPE,DESCRIPTION)
VALUES (1,'Savings', 'Savings account type'),
       (2,'Current', 'Current account type');

INSERT INTO
    CURRENCY (ID, CURRENCY_CODE, NAME)
VALUES (1,'AUD','Australian Dollar'),
       (2,'USD','United States Dollar');

INSERT INTO
    ACCOUNT (ACCOUNT_ID,USER_ID,ACCOUNT_NUMBER,ACCOUNT_NAME,ACCOUNT_TYPE,BALANCE_DATE,CURRENCY,OPENING_BALANCE)
    VALUES ('0001','123456','123456','Savings123',1,'2012-09-17',1,'1232.23'),
           ('0002','111111','654321','Savings124',1,'2012-09-20',1,'12.45'),
           ('0003','123456','654321','Savings124',1,'2020-01-01',1,'12.45');

INSERT INTO
    TRANSACTION (TRANSACTION_ID,ACCOUNT_ID,VALUE_DATE,CURRENCY,AMOUNT,TRANSACTION_NARRATIVE,TRANSACTION_TYPE)
    VALUES ('000023143','0001','2012-09-17',1,'250','ABC','Debit'),
           ('000323222','0002','2014-09-17',1,'120','ABC','Debit'),
           ('000323223','0001','2013-09-17',1,'34','ABC','Debit'),
           ('000323224','0001','2015-04-07',1,'12.50','ABC','Debit'),
           ('000323225','0003','2020-01-27',1,'50','ABC','Debit');
