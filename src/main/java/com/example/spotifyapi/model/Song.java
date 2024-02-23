package com.example.spotifyapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "songs")
public class Song implements Comparable<Song>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String album;
    private LocalDate releasedDate;
    @ManyToOne
    private Artist author;

    public Song() {
    }

    public Song(String name, String album, LocalDate releasedDate) {
        this.name = name;
        this.album = album;
        this.releasedDate = releasedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Artist getAuthor() {
        return author;
    }

    public void setAuthor(Artist author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{" +
                "Id: " + this.id +
                ", Name: " + this.name +
                ", Album: " + this.album +
                ", Released: " + this.releasedDate +
                ", Artist: " + this.author.getName() + "}";
    }

    @Override
    public int compareTo(Song song) {
        return this.name.compareToIgnoreCase(song.getName());
    }
}
