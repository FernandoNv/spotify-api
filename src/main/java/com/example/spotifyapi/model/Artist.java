package com.example.spotifyapi.model;

import com.example.spotifyapi.enums.ArtistType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist implements Comparable<Artist>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArtistType artistType;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch =FetchType.EAGER
    )
    private List<Song> songList = new ArrayList<>();

    public Artist() {}

    public Artist(String name, String artistType) {
        this.name = name;
        this.artistType = ArtistType.fromString(artistType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArtistType getArtistType() {
        return artistType;
    }

    public void setArtistType(ArtistType artistType) {
        this.artistType = artistType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return "Artist \n" +
                "Id: " + this.id + "\n" +
                "Name: " + this.name + "\n" +
                "Type: " + this.artistType + "\n" +
                "Songs: " + this.songList + "\n";
    }

    @Override
    public int compareTo(Artist artist) {
        return this.name.compareToIgnoreCase(artist.name);
    }
}
