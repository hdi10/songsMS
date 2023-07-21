DROP TABLE IF EXISTS usertable;

CREATE TABLE IF NOT EXISTS usertable(user_id VARCHAR(20) PRIMARY KEY, password VARCHAR(20) NOT NULL, firstName VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, token VARCHAR(100) NULL);

INSERT INTO usertable (user_id, password, firstName, lastName) VALUES ('bschmitt', '123', 'Burkhart', 'Schmitt');

INSERT INTO usertable (user_id, password, firstName, lastName) VALUES ('maxime', 'pass1234', 'Maxime', 'Muster');

INSERT INTO usertable (user_id, password, firstName, lastName, token) VALUES ('jane', 'pass1234', 'Jane', 'Muster', 'validToken');

INSERT INTO usertable (user_id, password, firstName, lastName, token) VALUES ('fritz', 'pass1234', 'Fritz', 'Muster', 'qwertyuiiooxd1a245');

