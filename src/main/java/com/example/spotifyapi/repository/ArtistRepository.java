package com.example.spotifyapi.repository;

import com.example.spotifyapi.model.Artist;
import com.example.spotifyapi.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameContainingIgnoreCase(String name);
    boolean existsByNameContainingIgnoreCase(String name);
    @Query(value = "SELECT s FROM Artist a JOIN a.songList s WHERE a.name ILIKE %:name%")
    List<Song> getSongsByArtistName(String name);
}
