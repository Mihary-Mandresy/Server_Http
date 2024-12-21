package aff;

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
            new Serveur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Spécifiez le fichier à ouvrir avec nano
        // String fileName = "start.sh";

        // // Construire la commande pour lancer nano
        // ProcessBuilder processBuilder = new ProcessBuilder("nano", fileName);

        // // Hériter du flux d'entrée/sortie de la console pour interagir avec nano
        // processBuilder.inheritIO();

        // try {
        //     // Démarrer le processus
        //     Process process = processBuilder.start();

        //     // Attendre que le processus se termine (fermeture de nano)
        //     int exitCode = process.waitFor();
        //     System.out.println("Nano a quitté avec le code : " + exitCode);
        // } catch (IOException | InterruptedException e) {
        //     e.printStackTrace();
        // }

    }
}