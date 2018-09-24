INSERT INTO USER (password, role, username)
VALUES ('$2a$10$09lF3qCzxvo0yZSonrQrZOkTb1pT7tNoLzPMG8VhDs55B1u90ulfe','ADMIN','admin');

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Build a tool that takes a string of text as input and encrypts it using a cipher, such as the Caesar cipher.','Intermediate','Implement a cipher', (SELECT uid FROM USER where uid=1), 1);

INSERT INTO IDEA (description, difficulty, name, uid, id)
VALUES ('Build a tool that takes a URL as input and returns the content of the URL as HTML or XML.','Intermediate','Web scraper', (SELECT uid FROM USER where uid=1), 2);
