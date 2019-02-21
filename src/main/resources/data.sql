-- CREATE TABLE USERS(id LONG AUTO_INCREMENT NOT NULL, email VARCHAR(255) UNIQUE, password VARCHAR(255), isAdmin BOOL, firstName VARCHAR(255), lastName VARCHAR(255), documentInfo VARCHAR(255), birthday DATETIME, isDeleted BOOL, PRIMARY KEY (id),UNIQUE (email));
INSERT INTO USERS (email, password, isAdmin, firstName, lastName, documentInfo, birthday, isDeleted) VALUES
('EMAIL@MAIL.COM',           '$2a$10$JvCWszTWV.jjPaRy.gqYfOpBzpntX3/eIjOGWO.BE5inOHxTzJmUi', true, 'Thomas1', 'Testyman1', '№1234 bestpassport ever', '2000-06-05 00:00:00', false),
('ExampleEmail2@google.com', '$2a$10$kPySdPurz7ObKctRfurc9eBOfgheI8kULb3f6CvhtHwf5uFCyJLV2', false,'Thomas2', 'Testyman2', '№1234 bestpassport ever', '2000-06-04 00:00:00', false),
('ExampleEmail3@google.com', '$2a$10$vWb35EcZlonHWRCYMAY.1ud/4MaaThFV9EGIanNhAmqD5juym2qV6', false,'Thomas3', 'Testyman3', '№1234 bestpassport ever', '2000-06-03 00:00:00', false),
('ExampleEmail4@google.com', '$2a$10$QSDvYQUH8iszQStjWwoVjuLM58Z9SCxE3ZvHTABTn1nTAdhoLPw22', false,'Thomas4', 'Testyman4', '№1234 bestpassport ever', '2000-06-02 00:00:00', false);

-- CREATE TABLE PLANES(id LONG AUTO_INCREMENT NOT NULL, model VARCHAR(255), businessPlacesNumber NUMBER, economPlacesNumber NUMBER, isDeleted BOOL, PRIMARY KEY (id));
INSERT INTO PLANES (model, businessPlacesNumber, economPlacesNumber, isDeleted) VALUES
('Airbus A380',     25,   500,  false),
('Boeing 777x',     30,   370,  false),
('Boeinig 747-8',   100,  900,  false);

-- CREATE TABLE FLIGHTS(id LONG AUTO_INCREMENT NOT NULL, planeId LONG NOT NULL, initialPrice LONG, departureAirport VARCHAR (255), arrivalAirport VARCHAR (255), departureDate DATETIME, arrivalDate DATETIME, isDeleted BOOL, luggagePrice LONG, placePriorityPrice LONG,businessPrice LONG,  PRIMARY KEY(id), FOREIGN KEY (planeId) REFERENCES PLANES);
INSERT INTO FLIGHTS (initialPrice,planeId, isDeleted, departureDate,arrivalDate,placePriorityPrice,businessPrice,luggagePrice, departureAirport, arrivalAirport) VALUES
('500','1', FALSE, '2019-02-11 11:00:00', '2019-02-11 13:00:00', '1000', '5000', '1500', 'MOSCOW', 'SAINT-PETERSBURG'),
('1200','2',FALSE,'2019-01-14 05:45:00', '2019-01-14 08:10:00','800', '10000', '1900', 'SAINT-PETERSBURG', 'MAGNITOGORSK'),
('1500','2',FALSE, '2019-02-21 22:50:00', '2019-02-22 01:25:00','600', '15000', '1800', 'MOSCOW', 'TOMSK'),
('2000', '3',FALSE, '2019-03-17 14:30:00', '2019-03-18 00:05:00','450', '8700', '900', 'TOMSK', 'HABAROVSK');

-- CREATE TABLE TICKETS(id LONG AUTO_INCREMENT NOT NULL, userId LONG NOT NULL, flightId LONG NOT NULL, isBusiness BOOL, hasLuggage BOOL, placePriority BOOL, purchaseDate DATETIME, price LONG, isDeleted BOOL, PRIMARY KEY (id), FOREIGN KEY (userId) REFERENCES USERS(id) ON UPDATE CASCADE, FOREIGN KEY (flightId) REFERENCES FLIGHTS(id) ON UPDATE CASCADE);
INSERT INTO TICKETS (userId, flightId, isBusiness, hasLuggage, placePriority, purchaseDate, price, isDeleted) VALUES
(1, 1, TRUE, TRUE, TRUE, '2019-01-01 11:00:00', 8050, false),
(1, 2, TRUE, TRUE, TRUE, '2019-01-12 05:45:00', 15021, false),
(2, 2, FALSE, TRUE, FALSE, '2019-11-01 05:45:00', 3165, false),
(3, 1, FALSE, FALSE, FALSE, '2019-02-01 11:00:00', 847,false);

