DROP TABLE IF EXISTS user;

CREATE TABLE user (
      id INT AUTO_INCREMENT  PRIMARY KEY,
      username VARCHAR(250) NOT NULL,
      password VARCHAR(250) NOT NULL,
      hobby VARCHAR(250) DEFAULT NULL
);

INSERT INTO user (username, password, hobby) VALUES
('orange1', '1414914', 'eat'),
('orange2', '810975', 'hearthstone'),
('orange3', '93589', 'sleep'),
('orange4', '010109', 'lol'),
('orange5', '9527', 'sleep'),
('orange6', '1000', 'sle1ep'),
('orange7', '10001', 'sl2eep'),
('orange8', '9289', 'sl3eep');