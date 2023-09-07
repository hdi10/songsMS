-- Tabellen löschen
DROP TABLE IF EXISTS has_song_lists;
DROP TABLE IF EXISTS contains_song;
DROP TABLE IF EXISTS song_list;
DROP TABLE IF EXISTS song;
DROP TABLE IF EXISTS user_account;

-- Tabellen erstellen
CREATE TABLE song
(
    song_id       SERIAL PRIMARY KEY,
    title         VARCHAR(100) NOT NULL,
    artist        VARCHAR(100),
    label         VARCHAR(100),
    released_year INTEGER
);

CREATE TABLE user_account
(
    user_id    VARCHAR(20) PRIMARY KEY,
    password   VARCHAR(200) NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    token      VARCHAR(200)
);

CREATE TABLE song_list
(
    song_list_id SERIAL PRIMARY KEY,
    owner_id     VARCHAR(20) NOT NULL,
--     REFERENCES user_account (user_id), -------> ACHTUNG FK rausgenommen
    name         VARCHAR(50) NOT NULL,
    is_private   BOOLEAN     NOT NULL
);

CREATE TABLE contains_song
(
    song_list_id INTEGER REFERENCES song_list (song_list_id),
    song_id      INTEGER REFERENCES song (song_id),
    PRIMARY KEY (song_list_id, song_id)
);

----- ACHTUNG auch rausgenommen

/*CREATE TABLE has_song_lists
(
    user_id      VARCHAR(20) REFERENCES user_account (user_id),
    song_list_id INTEGER REFERENCES song_list (song_list_id),
    PRIMARY KEY (user_id, song_list_id)
);*/

-- Transaktion beginnen
BEGIN;

-- Daten einfügen
-- Hier füge ich keine SongId mehr explizit ein, da es automatisch generiert wird.
-- Für die anderen Tabellen folgt dasselbe Prinzip.

-- Song-Daten
INSERT INTO song(title, artist, label, released_year)
VALUES ('MacArthur Park', 'Richard Harris', 'Dunhill Records', 1968),
       ('Afternoon Delight', 'Starland Vocal Band', 'Windsong', 1976),
       ('Muskrat Love', 'Captain and Tennille', 'A&M', 1976),
       ('Sussudio', 'Phil Collins', 'Virgin', 1985),
       ('We Built This City', 'Starship', 'Grunt/RCA', 1985),
       ('Achy Breaky Heart', 'Billy Ray Cyrus', 'PolyGram Mercury', 1992),
       ('What Up?', '4 Non Blondes', 'Interscope', 1993),
       ('Who Let the Dogs Out?', 'Baha Men', 'S-Curve', 2000),
       ('My Humps', 'Black Eyed Peas', 'Universal Music', 2005),
       ('Chinese Food', 'Alison Gold', 'PMW Live', 2013);

-- User-Daten
INSERT INTO user_account(user_id, password, first_name, last_name, token)
VALUES ('bschmitt', '123', 'Burkhart', 'Schmitt', NULL),
       ('maxime', 'pass1234', 'Maxime', 'Muster', NULL),
       ('jane', 'pass1234', 'Jane', 'Muster', 'validToken'),
       ('fritz', 'pass1234', 'Fritz', 'Muster', 'qwertyuiiooxd1a245');

-- SongList-Daten
INSERT INTO song_list(owner_id, name, is_private)
VALUES ('maxime', 'MaximesPrivate', TRUE),
       ('maxime', 'MaximesPublic', FALSE),
       ('jane', 'JanesPrivate', TRUE),
       ('jane', 'JanesPublic', FALSE);

-- Contains-Daten
INSERT INTO contains_song(song_list_id, song_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 1),
       (4, 2);

-- Commit
COMMIT;
