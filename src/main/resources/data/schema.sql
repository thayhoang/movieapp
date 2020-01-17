DROP TABLE IF EXISTS favorites;

CREATE TABLE favorites (
  userId INTEGER DEFAULT NULL,
  movieId INTEGER DEFAULT NULL
);

DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
  id INTEGER auto_increment,
  title varchar(100) DEFAULT NULL,
  description varchar(250) DEFAULT NULL,
  trailer varchar(200) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INTEGER auto_increment,
  username varchar(45) DEFAULT NULL,
  password varchar(45) DEFAULT NULL,
  role varchar(45) DEFAULT NULL,
  fullName varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);
