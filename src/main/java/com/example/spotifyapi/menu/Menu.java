package com.example.spotifyapi.menu;

import com.example.spotifyapi.model.Artist;
import com.example.spotifyapi.model.Song;
import com.example.spotifyapi.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ArtistRepository artistRepository;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    public Menu(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    private void showOptions(){
        System.out.println("""
                Spotify API Example
                1 - Cadastrar artistas
                2 - Cadastrar músicas
                3 - Listar músicas
                4 - Buscar músicas por artistas
                5 - Pesquisar dados sobre um artista
                                
                9 - Sair
                """);
    }

    public void showMenu(){
        int option = -1;
        while (option != 9){
            showOptions();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    createArtist();
                    break;
                case 2:
                    createSong();
                    break;
                case 3:
                    getSongs();
                    break;
                case 4:
                    getSongsByArtist();
                    break;
                case 5:
                    getArtistData();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida...");
            }

        }
    }

    private void getArtistData() {
        System.out.println("Informação do spotify");
    }

    private void getSongsByArtist() {
        String name = getArtistName();

        List<Song> songsByArtistName = artistRepository.getSongsByArtistName(name);
        songsByArtistName.forEach(System.out::println);
    }

    private void getSongs() {
        List<Artist> artistList = artistRepository.findAll();
        List<Song> songList = artistList
                .stream()
                .flatMap(a -> a.getSongList().stream())
                .toList();

        songList.forEach(System.out::println);
    }

    private String getArtistName(){
        System.out.println("Cadastrar artista");
        System.out.println("Digite o nome do artista");
        String name = scanner.nextLine();

        return name;
    }

    private void createSong() {
        String name = getArtistName();

        Optional<Artist> artistFound = artistRepository.findByNameContainingIgnoreCase(name);

        if (artistFound.isEmpty()){
            System.out.println(("Artista ainda não cadastrado."));
            return;
        }

        Artist artist = artistFound.get();

        String option = "s";
        while (!option.equalsIgnoreCase("n")){
            saveSong(artist);
            System.out.println("Deseja cadastrar mais músicas? [S/N]");
            option = scanner.nextLine();
        }
    }

    private void saveSong(Artist artist) {
        System.out.println("Digite o nome da música");
        String name = scanner.nextLine();

        System.out.println("Digite o nome do album");
        String album = scanner.nextLine();

        System.out.println("Digite a data de lançamento: (dd/mm/aaaa)");
        String releasedDateString = scanner.nextLine();

        LocalDate releasedDate = LocalDate.from(dateTimeFormatter.parse(releasedDateString));

        Song song = new Song(name, album, releasedDate);
        song.setAuthor(artist);
        artist.getSongList().add(song);

        artistRepository.save(song.getAuthor());
    }

    private void createArtist() {
        String name = getArtistName();

        if (artistRepository.existsByNameContainingIgnoreCase(name)){
            System.out.println(("Artista já cadastrado."));
            return;
        }

        System.out.println("Informe o tipo de artista: (solo, dupla, banda)");
        String artisType = scanner.nextLine();

        Artist newArtist = new Artist(name, artisType);
        artistRepository.save(newArtist);
    }
}
