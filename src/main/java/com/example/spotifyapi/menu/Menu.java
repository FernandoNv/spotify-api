package com.example.spotifyapi.menu;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    Menu(){

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
        System.out.println("Informações de um artista");
    }

    private void getSongsByArtist() {
        System.out.println("Listat música por artista");
    }

    private void getSongs() {
        System.out.println("Listar músicas");
    }

    private void createSong() {
        System.out.println("Cadastrar música");
    }

    private void createArtist() {
        System.out.println("Cadastrar artista");
    }
}
