package aff;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import client.GestionClient;
import server.Log;
import server.Serveur;
import java.io.*;

public class Main {
    
    public static void main(String[] args) {
        try {
            // ! raha sendra stoppena force ilay serveur ohatra atao control + C
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                Serveur.clearSession();
            }));

            Serveur ser = new Serveur();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}