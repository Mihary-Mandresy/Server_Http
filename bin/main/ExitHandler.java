package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

import server.Serveur;
import server.frame.ConfigHandler;

public class ExitHandler implements Runnable {

    private ServerSocket srv;
    
    public ExitHandler(ServerSocket srv) {
        this.srv = srv;
    }

    /**
     * Ito indray dia zavatra atao rah toa ka stoppena ilay serveur Http
     */
    @Override
    public void run() {
        try (BufferedReader sysInp = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String mess = sysInp.readLine().trim();
                // ! oe raha manao manoratra exit sur terminal dia vonona ilay serveur
                if (mess.equalsIgnoreCase("exit")) {
                    srv.close();
                    break;
                } else if (mess.equalsIgnoreCase("conf")) {
                    new ConfigHandler();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
