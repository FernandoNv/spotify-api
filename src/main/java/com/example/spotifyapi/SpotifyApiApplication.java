package com.example.spotifyapi;

import com.example.spotifyapi.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotifyApiApplication implements CommandLineRunner {
    @Autowired
    private Menu menu;

    public static void main(String[] args) {
        SpringApplication.run(SpotifyApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menu.showMenu();
    }
}
