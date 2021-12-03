CREATE DATABASE IF NOT EXISTS MYSQL89_02_secondhand;
USE MYSQL89_02_secondhand;

DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `Administrator` boolean DEFAULT FALSE,
  PRIMARY KEY (Email)
);

CREATE TABLE PRODUCT (
  `Title` varchar(100) NOT NULL,
  `Owner` varchar(45) NOT NULL,
  `Category` varchar(45) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `Image` longblob DEFAULT NULL,
  `Price` int(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (Title, Owner),
  FOREIGN KEY (Owner) REFERENCES USER(email)
);

INSERT INTO `User` (Email, Password, Name, Surname, City, Administrator) VALUES ('karendahlaarhus@gmail.com', 'password', 'Karen', 'Aarhus', 'Oslo', false);




