package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import client.HandlerData;
import client.HandlerPhpData;
import server.LogHandler;
import server.ResponseHTTP;
import server.Serveur;

public class GestionClient implements Runnable {

    private Socket clientSocket;
    private String methode;
    private String  path;
    private LogHandler log = new LogHandler();

    // ? Constructeur

    public GestionClient(Socket socket) {
        this.clientSocket = socket;
    }

    // * Methode Override

    private String getPostData(BufferedReader reader) throws Exception {
        StringBuilder bld = new StringBuilder();
        while (reader.ready()) {
            bld.append((char)reader.read());
        }

        String[] str = bld.toString().split("[\\n\\r]{3,}");
        if (str.length == 1) {
            return null;
        }
        return str[1];
    }

    private String getRealPath() {
        String[] splt = path.split("\\?");
        return splt[0];
    }

        /**
         * Manao initialisation anleh client fotsiny , izay maka anleh path (chemin) sy ilay methode oe inona no methode tonga tao
         * @param str ilay string angalana anireo roa ireo
         * @throws Exception
         */
        private void initClient(String str) throws Exception {
            String[] splt = str.split(" ");
            if (splt.length != 3) throw new Exception("Probleme d'initialisation de client");
            methode = splt[0];
            path =  Serveur.root + splt[1];
            log.accesLog(clientSocket.getInetAddress().getHostAddress(), str);
        }

    /**
     * Affichage des reponses pour le client (navigateur ohatra)
     */
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
            try {
                initClient(in.readLine());
            
                // String line;
                // while ((line = in.readLine()) != null && !line.isEmpty()) {
                //     System.out.println(line);
                // 

                File fl = new File(path);

                // ! mijery raha oe directory izy no miditra dia verifiena rah misy index ao dia hidirana

                if (fl.isDirectory()) {
                    File[] listeFile = fl.listFiles();
                    for (File el : listeFile) {
                        if (el.getName().equalsIgnoreCase("index.html")) { // ! si html
                            HandlerData dt = new HandlerData(el.getPath(), clientSocket.getOutputStream());
                            dt.manomeReponse();
                            return;
                        } else if (el.getName().equalsIgnoreCase("index.php")) { // ! si php
                            HandlerPhpData dataPhp = new HandlerPhpData(el.getPath());
                            out.println(dataPhp.giveDataPhp(methode, getPostData(in)));
                            return;
                        }
                    }
        
                }

                // ! miafficher anleh contenue tokony apoitra any amin'ny navigateur

                if (getRealPath().endsWith(".php")) { // ! si php
                    HandlerPhpData phpData = new HandlerPhpData(path);
                    out.println(phpData.giveDataPhp(methode, getPostData(in)));
                } else { // ! si autre fichier
                    HandlerData dt = new HandlerData(getRealPath(), clientSocket.getOutputStream());
                    String rep = dt.manomeReponse();
                    if (rep != null) {
                        out.println(rep);
                    }
                }

            } catch (FileNotFoundException e) {
                out.println(ResponseHTTP.error404());
                log.errLog(clientSocket.getInetAddress().getHostAddress(), e.getMessage());
                throw e;
            } catch (Exception e) {
               out.println( ResponseHTTP.error500());
               log.errLog(clientSocket.getInetAddress().getHostAddress(), e.getMessage());
               throw e;
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            // e.printStackTrace();
        }
    }
}