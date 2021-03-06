DROP DATABASE bank_project;
CREATE DATABASE bank_project;
USE bank_project;
    CREATE USER 'user' IDENTIFIED WITH mysql_native_password BY 'user';
    GRANT ALL on bank_project.* TO 'user';

     CREATE TABLE BANKS
       (
          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(20),
          address VARCHAR(50)
       );


       CREATE TABLE ACCOUNTS
       (
          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(20),
          money float,
          iban VARCHAR(20)
       );


       CREATE TABLE PAYMENTS
       (
          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
          bankId INT,
          userId INT,
          amount float,
          FOREIGN KEY(bankId) REFERENCES BANKS(id),
          FOREIGN KEY(userId) REFERENCES ACCOUNTS(id)
       );


