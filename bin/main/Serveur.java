package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import client.GestionClient;

public class Serveur {

    private int port;
    public static String root;
    private ServerSocket server;
    public static  String racineDeProjet;
    public static boolean activationPhp;
    public static String dirSeparator = System.getProperty("file.separator");

    // ? Constructeur

    public Serveur() throws Exception {
        inConstructeur();
    }

    private void inConstructeur() throws Exception {
        definirRacineDeProjet();
        getDataInFile();
        initServer();
    }

    private void definirRacineDeProjet() throws Exception {
        String rt = System.getProperty("user.dir");
        racineDeProjet =  rt.substring(0, rt.length() - 9);
    }

    // $ Fonction tsotra


    public static void editConfig() throws Exception {
        ProcessBuilder process = new ProcessBuilder("gedit",  ".." + dirSeparator + ".." + dirSeparator + "conf" + dirSeparator + "serveur.conf");
        Process prs = process.start();
        prs.waitFor();
    }

        public static String rootWithSlash() {
            return root + dirSeparator;
        }

        /**
         * Manao initialisation anleh configuration anleh serveur mihintsy, ilay avy any anaty fichier (serveur.conf ohatra)
         * Ohatra oe maka anleh port sy ilay racine de dossier
         * @param map ilay objet map izay micontenir anilay configuration rehetra ka ao aminio methode fotsiny no maka anleh izy aveo
         * @throws Exception
         */
        private void initConfig(HashMap<String, String> map) throws Exception {
            try {
                this.port = Integer.parseInt(map.get("port"));
                this.activationPhp = Boolean.parseBoolean(map.get("php"));

                File fl = new File(racineDeProjet + map.get("root"));

                if (fl.exists()) {
                    root = racineDeProjet +  map.get("root");
                } else {
                    throw new Exception("Verifier bien votre le dossier racine de votre projet");
                }

            } catch (Exception e) {
                throw new Exception("Verifier bien votre fichier de configuration");
            }
        }

    /**
     * Maka anilay configuration rehetra any anaty fichier de configuration (../../conf/serveur.conf)
     * @throws Exception
     */
    private void getDataInFile() throws Exception {
        File fl = new File("../../conf/serveur.conf");
        if (!fl.exists()) throw new Exception("Le fichier de configuration doit a sa place");
        
        try (FileReader rd = new FileReader(fl);
            BufferedReader read = new BufferedReader(rd);
        ) {
            HashMap<String, String> map = new HashMap<>();

            String line;
            while ((line = read.readLine()) != null) {
                String[] splt = line.split("\\s*=\\s*");
                map.put(splt[0], splt[1]);
            }

            // ! Ato no miantso anlilay fonction iniConfig mba initialisena anilay configuration ka omena anilay objet map
            initConfig(map);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Initialisation anleh serveur rah efa azo daoly leh configuration necessaire
     * Izany oe ao zany no mamelona anleh serveur sy manao anleh gestion miger anleh Thread anleh client 
     */
    private void initServer() {
        System.out.println("Serveur en cours de démarrage...");
        // ! initalisation du serveur socket
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré et en attente de connexions sur le port " + port);
            this.server = serverSocket;

            Thread exit = new Thread(new ExitHandler(server));
            exit.start();

            while (true) {
                Socket clientSocket = serverSocket.accept(); // ! maka anilay client izay acceptenleh server
                // ! manambotra objet de type GestionClient mba mora higerena anleh client
                GestionClient clientHandler = new GestionClient(clientSocket);
                // ! Gestion des Threads omena anilay client
                Thread thread = new Thread(clientHandler);
                // ! mamelona anilay thread
                thread.start();
            }
        } catch (SocketException e) {
            System.out.println("Server closed");
        } catch (IOException e) {
            System.err.println("Erreur du serveur : " + e.getMessage());
            // e.printStackTrace();
        }
    }

    // ! Getter

    public int getPort() {
        return port;
    }

    public ServerSocket getServer() {
        return server;
    }

    // ! Setter

    public void setPort(int port) {
        this.port = port;
    }
}