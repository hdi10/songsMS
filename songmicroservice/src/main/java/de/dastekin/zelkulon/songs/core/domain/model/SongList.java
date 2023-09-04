/**
 * Dastekin created on 21.07.2023 the SongList-Class inside the package - de.dastekin.zelkulon.songs.core.domain.model
 */

package de.dastekin.zelkulon.songs.core.domain.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "SongLists")
public class SongList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SongListsId")      //TODO hier überlegen ob nicht in der table sogar id reicht
    private Integer id;

    @Column(name="OwnerId")
    private String ownerId;

    @Column(name="Name")
    private String name;

    @Column(name="IsPrivate")
    private Boolean isPrivate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER) //TODO vielleicht den fetch type ändern auf lazy um die performance zu verbessern
    @JoinTable(
            name = "contains",
            joinColumns = {@JoinColumn(name = "SongListsId" , referencedColumnName = "SongListsId")},
            inverseJoinColumns = {@JoinColumn(name = "SongId", referencedColumnName = "SongId")})
    //TODO hier orderby?
    //@OrderBy(value = "id")
    private Set<Song> songList = new HashSet<>();

    //TODO BoilerPlate Code Löschen --> Lombok
    /*public SongList() {
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
*/
    public void addSong(Song song) {
        this.songList.add(song);
    }

    //TODO BoilerPlate Code Löschen --> Lombok
    /*@Override
    public String toString() {
        return "SongList{" +
                "id=" + id +
                ", ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", isPrivate=" + isPrivate +
                ", songList=" + songList +
                '}';
    }*/
}
