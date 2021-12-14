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
	`Current` boolean DEFAULT FALSE,
	PRIMARY KEY (Email)
);

CREATE TABLE PRODUCT (
	`Id` varchar(36) NOT NULL,
	`Title` varchar(100) NOT NULL,
	`Owner` varchar(45) NOT NULL,
	`Category` varchar(45) NOT NULL,
	`Description` varchar(500) DEFAULT NULL,
	`Image` varchar(500) DEFAULT NULL,
	`Price` float(45) NOT NULL,
	`Status` varchar(45) NOT NULL,
	PRIMARY KEY (Id)
);

INSERT INTO `User` (Email, Password, Name, Surname, City, Administrator) VALUES ('lois.hull@gmail.com', 'password', 'Lois', 'Hull', 'Madrid', true);
INSERT INTO `User` (Email, Password, Name, Surname, City, Administrator) VALUES ('kathryn.irwin@hotmail.com', 'password', 'Kathryn', 'Irwin', 'Madrid', false);
INSERT INTO `User` (Email, Password, Name, Surname, City, Administrator) VALUES ('isla-buckley@outlook.com', 'password', 'Isla', 'Buckley', 'Madrid', false);
INSERT INTO `User` (Email, Password, Name, Surname, City, Administrator) VALUES ('phil.ruiz@gmail.com', 'password', 'Philip', 'Ruiz', 'Madrid', true);
INSERT INTO `User` (Email, Password, Name, Surname, City, Administrator) VALUES ('fergus-bishop@outlook.com', 'password', 'Fergus', 'Bishop', 'Madrid', false);

INSERT INTO `Product` (Id, Title, Owner, Category, Description, Image, Price, Status) VALUES (
'0343ae28-97fe-49bb-8f69-0499c9c256d3', 'Barbie house', 'lois.hull@gmail.com', 'For childeren and babies', 
'Im selling my kids barbie house since they have grown out of it and dont like barbies anymore. It has been used for many years, but is still in good condition. I hope this house can find some new ownersso it can continue to bring joy to others as it has given my little girls. Price can be discussed.',
"https://image.smythstoys.com/zoom/167209.jpg", 20, 'available');

INSERT INTO `Product` (Id, Title, Owner, Category, Description, Image, Price, Status) VALUES (
'2ed4b054-b5d0-42e1-9df6-dedf462a60ff', 'Swimming feet', 'kathryn.irwin@hotmail.com', 'Sports and Outdoor equipment', 
'Adult swimming feet. Good condition. Only used a couple of times.',
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStu4pgjX0QqPHtQp1ghS9F0lfwvP1zpHju1Q&usqp=CAU", 8.5, 'available');

INSERT INTO `Product` (Id, Title, Owner, Category, Description, Image, Price, Status) VALUES (
'bad63485-fba5-44ae-9b89-2917e71ca068', 'Prom dress', 'isla-buckley@outlook.com', 'Clothing, accessories and make-up', 
'The prom dress did not fit me so Im selling it. I have only tried it on so it is brand new. I bought it from zalando and is selling it for half the price. Good deal!',
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoEXj9U8Ht8y4U31QqrlXHprqyiy0eK_HatQ&usqp=CAU", 75, 'available');

INSERT INTO `Product` (Id, Title, Owner, Category, Description, Image, Price, Status) VALUES (
'88d145c3-42a8-4dca-84a4-675bb9dcaf42', 'Race car', 'phil.ruiz@gmail.com', 'Cars and Motorcycles', 
'Selling a broken race car. Buy your fixer-upper dream or sell it for parts! Only sky is the limit with this race car. Selling it really cheap. Best deal you will get. Send me a mail for dsicussion of pick up.',
"https://p0.pikist.com/photos/953/629/car-crash-race-automobile-speed-smash-wreck-damage-broken.jpg", 100, 'available');

INSERT INTO `Product` (Id, Title, Owner, Category, Description, Image, Price, Status) VALUES (
'4e80d939-ff1a-46d1-9998-43b7cf69b6ac', 'New tent', 'fergus-bishop@outlook.com', 'Sports and Outdoor equipment', 
'Pacific Pass Tienda de campaña resistente al agua portátil con sobretecho. Original price was 39 from amazon.', 
"https://m.media-amazon.com/images/I/714ebKVsLpL._AC_SL1500_.jpg", 25, "available");


