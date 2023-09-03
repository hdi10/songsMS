/**
 * Dastekin created on 12.07.2023 the Song-Class inside the package - de.dastekin.zelkulon.songs.core.domain.model
 */
package de.dastekin.zelkulon.songs.core.domain.model;

import jakarta.persistence.*;

/**
 * Model class for Song
 */
@Entity
@Table(name = "songs")  //table vielleicht doch einfach nur Song nennen
public class Song {

    @Id // kennzeichnet das Identitätsattribut entspricht dem PK (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // bedeutet, dass der PK automatisch durch die DB vergeben wird
    @Column(name = "id")
    private Integer id = null;
    private String title = null;
    private String artist = null;
    private String label = null;
    private Integer released = null;

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


    //////////////////////////////////////////////////////////////////////////////////////
    //////////     my builder    ////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates builder to build {@link Song}.
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    private Song(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.artist = builder.artist;
        this.label = builder.label;
        this.released = Integer.valueOf(builder.released);
    }


    /**
     * Builder to build {@link Song}.
     */
    public static final class Builder {
        private int id;
        private String title;
        private String artist;
        private String label;
        private String released;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withUserId(String title) {
            this.title = title;
            return this;

        }

        public Builder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public Builder withLabel(String label) {
            this.label = label;
            return this;
        }

        public Builder withReleased(String released) {
            this.released = released;
            return this;
        }

        public Song build() {
            return new Song(this);
        }
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
