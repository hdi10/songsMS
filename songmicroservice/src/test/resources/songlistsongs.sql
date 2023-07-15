/*DROP TABLE IF EXISTS song_list_songs;

CREATE TABLE IF NOT EXISTS song_list_songs(song_list_id INTEGER NOT NULL,song_id INTEGER NOT NULL,PRIMARY KEY(song_list_id, song_id),FOREIGN KEY(song_list_id) REFERENCES song_list(song_list_id),FOREIGN KEY(song_id) REFERENCES songs(id));
*/
