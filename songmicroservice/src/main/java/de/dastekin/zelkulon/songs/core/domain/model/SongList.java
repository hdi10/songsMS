/**
 * Dastekin created on 21.07.2023 the SongList-Class inside the package - de.dastekin.zelkulon.songs.core.domain.model
 */
package de.dastekin.zelkulon.songs.core.domain.model;
import de.dastekin.zelkulon.auth.core.domain.model.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "song_list")
public class SongList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_list_id")
    private Integer song_list_id;


  ////////////////////////////////////////////////////////////////////
    /////////////////////////// //TODO Relation ///////////////////////////
    ////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @ManyToOne
    //@JoinColumn(name="user_id")
    @JoinTable(name = "song_list",
            joinColumns = @JoinColumn(name = "song_list_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    private String name;
    @Column(name="is_private")
    private boolean isPrivate;

    /**
     *
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "songs_list_song",
            joinColumns = @JoinColumn(name = "song_list_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Song> songSet;

    ////////////////////////////////////////////////////////////////////
    /////////////////////////// //TODO Relation ///////////////////////////
    ////////////////////////////////////////////////////////////////////

    public SongList() {
    }

    public SongList(boolean isPrivate,User user,String name, Set<Song> songSet) {
        this.user = user;
        this.songSet = songSet;
        this.name = name;
        this.isPrivate = isPrivate;
    }

    @Transient
    public String getUserId () {
        return this.user.getUserId();
    }

    @Transient
    public String getName() {
        return this.name;
    }

    @Transient
    public Integer getId() {
        return song_list_id;
    }
}
