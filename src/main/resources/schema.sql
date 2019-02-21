DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS PLANES;
DROP TABLE IF EXISTS FLIGHTS;
DROP TABLE IF EXISTS TICKETS;

CREATE TABLE USERS
(
  id                        LONG              NOT NULL      AUTO_INCREMENT,
  email                     VARCHAR(255)                                        UNIQUE,
  password                  VARCHAR(255),
  isAdmin                   BOOL,
  firstName                 VARCHAR(255),
  lastName                  VARCHAR(255),
  documentInfo              VARCHAR(255),
  birthday                  DATETIME,
  isDeleted                 BOOL,
  PRIMARY KEY (id),
);

CREATE TABLE PLANES
(
  id                        LONG              NOT NULL      AUTO_INCREMENT,
  model                     VARCHAR(255)      NOT NULL,
  businessPlacesNumber      NUMBER            NOT NULL,
  economPlacesNumber        NUMBER            NOT NULL,
  isDeleted                 BOOL              NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE FLIGHTS
(
  id                        LONG              NOT NULL      AUTO_INCREMENT,
  planeId                   LONG              NOT NULL,
  initialPrice              LONG,
  departureAirport          VARCHAR (255)     NOT NULL,
  arrivalAirport            VARCHAR (255)     NOT NULL,
  departureDate             DATETIME          NOT NULL,
  arrivalDate               DATETIME          NOT NULL,
  isDeleted                 BOOL,
  luggagePrice              LONG,
  placePriorityPrice        LONG,
  businessPrice             LONG,
  PRIMARY KEY(id),
  FOREIGN KEY (planeId) REFERENCES PLANES(id)
);

CREATE TABLE TICKETS
(
  id                        LONG              NOT NULL      AUTO_INCREMENT,
  userId                    LONG              NOT NULL,
  flightId                  LONG              NOT NULL,
  isBusiness                BOOL,
  hasLuggage                BOOL,
  placePriority             BOOL,
  purchaseDate              DATETIME,
  price                     LONG,
  isDeleted                 BOOL,
  PRIMARY KEY (id),
  FOREIGN KEY (userId) REFERENCES USERS(id) ON UPDATE CASCADE,
  FOREIGN KEY (flightId) REFERENCES FLIGHTS(id) ON UPDATE CASCADE
);