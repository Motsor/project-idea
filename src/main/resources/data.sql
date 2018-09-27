INSERT INTO USER (password, role, username)
VALUES ('$2a$10$09lF3qCzxvo0yZSonrQrZOkTb1pT7tNoLzPMG8VhDs55B1u90ulfe','ADMIN','admin');

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Build a tool that takes a string of text as input and encrypts it using a cipher, such as the Caesar cipher.','Intermediate','Implement a cipher', (SELECT uid FROM USER where uid=1), 1);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Build a tool that takes a URL as input and returns the content of the URL as HTML or XML.','Intermediate','Web scraper', (SELECT uid FROM USER where uid=1), 2);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Build a simple FTP client. As a bonus challenge, support secure file transfer.','Advanced','FTP Client', (SELECT uid FROM USER where uid=1), 3);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('To understand HTTP deeply, build an HTTP server.','Intermediate','HTTP Server', (SELECT uid FROM USER where uid=1), 4);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('This is a project that is technically challenging and will deepen your understanding of how computers and operating systems work. You might wish to start with the free (and cleverly named) book, Operating Systems: From 0 to 1.','Advanced','Simple operating system', (SELECT uid FROM USER where uid=1), 5);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Build a tool to track how much data you have downloaded or uploaded on the internet. Have it email you a weekly report of your usage. As a bonus challenge, predict peak usage times.','Advanced','Bandwidth monitor', (SELECT uid FROM USER where uid=1), 6);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Create an app that takes a URL as input and sends you a text message when a request to the URL returns a response code other than 200 (OK).','Advanced','Text message downtime alerter', (SELECT uid FROM USER where uid=1), 7);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Build a calculator you can use to track the rise or fall of your net worth on a monthly basis.','Intermediate','Net worth calculator and tracker', (SELECT uid FROM USER where uid=1), 8);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Try to write an engine that can play chess against a human opponent using a Universal Chess Interface compatible GUI, such as XBoard.','Advanced','A chess engine', (SELECT uid FROM USER where uid=1), 9);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Build a tool to classify whether an email is or isn’t spam based on the content alone.','Intermediate','A spam classifier', (SELECT uid FROM USER where uid=1), 10);