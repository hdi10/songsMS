/**
 * Dastekin created on 21.07.2023 the SongList-Class inside the package - de.dastekin.zelkulon.songs.core.domain.model
 */

package de.dastekin.zelkulon.songs.core.domain.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Model class for SongList
 */
@Entity
@Table(name = "song_list")
public class SongList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_list_id")      //TODO hier überlegen ob nicht in der table sogar id reicht
    private Integer id;

    @Column(name="owner_id")
    private String ownerId;

    @Column(name="name")
    private String name;

    @Column(name="is_private")
    private Boolean isPrivate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER) //TODO vielleicht den fetch type ändern auf lazy um die performance zu verbessern
    @JoinTable(
            name = "contains_song",
            joinColumns = {@JoinColumn(name = "song_list_id" , referencedColumnName = "song_list_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "song_id")})
    //TODO hier orderby?
    //@OrderBy(value = "id")
    private Set<Song> songList = new HashSet<>();

    //TODO BoilerPlate Code Löschen --> Lombok
    public SongList() {
    }

    public SongList(Integer id, String ownerId, String name, Boolean isPrivate, Set<Song> songList) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.isPrivate = isPrivate;
        this.songList = songList;
    }

    public SongList(String name, Boolean isPrivate, Set<Song> songList) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.songList = songList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(value = "isPrivate") //Todo hier überlegen ob das so richtig ist
    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Set<Song> getSongList() {
        return songList;
    }

    public void setSongList(Set<Song> songList) {
        this.songList = songList;
    }

    public void addSong(Song song) {
        this.songList.add(song);
    }

    //TODO BoilerPlate Code Löschen --> Lombok
    @Override
    public String toString() {
        return "SongList{" +
                "id=" + id +
                ", ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", isPrivate=" + isPrivate +
                ", songList=" + songList +
                '}';
    }
}
