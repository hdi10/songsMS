/**
 * Dastekin created on 12.07.2023 the Song-Class inside the package - de.dastekin.zelkulon.songs.core.domain.model
 */
package de.dastekin.zelkulon.songs.core.domain.model;

import jakarta.persistence.*;
/**
 * Model class for Song
 */

@Entity
@Table(name = "song")  //table vielleicht doch einfach nur Song nennen
public class Song {

    @Id // kennzeichnet das Identitätsattribut entspricht dem PK (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // bedeutet, dass der PK automatisch durch die DB vergeben wird
    @Column(name = "song_id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="artist")
    private String artist;

    @Column(name="label")
    private String label;

    @Column(name="released_year")
    private Integer released;


    //TODO BoilerPlate Code Löschen --> Lombok

    /* braucht das um leere objekte zu erzeugen
        wenn ein definierter KOnstruktor vorhanden
        um dann zu befüllen */
    public Song() {
    }

    public Song(Integer id, String title, String artist, String label, Integer released) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.released = released;
    }

    public Song(String title, String artist, String label, Integer released) {
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.released = released;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getLabel() {
        return label;
    }

    public Integer getReleased() {
        return released;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }


    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", label='" + label + '\'' +
                ", released='" + released + '\'' +
                '}';
    }
}
