package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.http.HttpResponse;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import server.*;

public class HandlerPhpData {
    
    private String path;

    // ? Constructeur

    public HandlerPhpData(String path) {
        this.path = path;
    }

    private String getRealPath() {
        String[] splt = path.split("\\?");
        return splt[0];
    }
    

    /**
     * Manao interpretation anilay script php 
     * @param cmd   io ilay commande omena anleh ProcessBuilder
     * @return Miretourener anlilay interpretation anleh script azo avy any omena anilay methode givedata eo ambany
     * @throws Exception
     */
    private String readByProcessBuilder(Vector<String> cmd) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        processBuilder.redirectErrorStream(true);
        Process process =  processBuilder.start();

        StringBuilder bld = new StringBuilder();

        try (BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = read.readLine()) != null) {
                bld.append(line + "\n");
            }
            return bld.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    // $ Fonction tsotra
    
    /**
     * Mamerina anilay reponse efa vita interpretation any amilay client (navigateur ohatra)
     * @param methode ilay methode oe post io sa get
     * @param postData io ilay donne post izay alefan'ilay client
     * @return Mamerina Resultat omena anilay client
     * @throws Exception
     */
    public String giveDataPhp(String methode, String postData) throws Exception {
        String[] splt = path.split("\\?");

        // Manomana anilay commande alefa any amleh methode readByProcessBuilder

        Vector<String> cmd = new Vector<>();
        cmd.add("php");
        cmd.add("-r");

        String include = "include('" + splt[0] + "')";

        if (methode.equals("GET")) {
            String data = "";
            if (splt.length == 2) {
                data = splt[1];
            }
            cmd.add("if (session_id('sess') == null) { session_id('sess'); } parse_str('" + data + "', $_GET); " + include + ";");
        } else if (methode.equals("POST")) {
            cmd.add("if (session_id('sess') == null) { session_id('sess'); } parse_str('" + postData + "', $_POST); " + include + ";"); 
        }

        return ResponseHTTP.response200(readByProcessBuilder(cmd), getRealPath());
    }

}