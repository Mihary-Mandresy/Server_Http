package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

import server.Serveur;

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
                // ! oe raha manao manoratra exit sur terminal dia vonona ilay serveur
                if (sysInp.readLine().trim().equalsIgnoreCase("exit")) {
                    srv.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
