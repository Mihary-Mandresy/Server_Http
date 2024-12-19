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
    private Pattern chkHeader = Pattern.compile("header(?=\\()");

    // ? Constructeur

    public HandlerPhpData(String path) {
        this.path = path;
    }

    private String getRealPath() {
        String[] splt = path.split("\\?");
        return splt[0];
    }

    private String checkHeader(String filePath) throws Exception {
        File fl = new File(filePath);
        try (FileReader rd = new FileReader(fl);
        BufferedReader read = new BufferedReader(rd)) {

            StringBuilder bld = new StringBuilder();
            String line;
            while ((line = read.readLine()) != null) {
                bld.append(line + "\n");
            }
            Matcher mt = chkHeader.matcher(bld.toString());
            if (mt.find()) {
                return bld.toString().replaceAll("header(?=\\()", "echo")
                .replaceAll("<\\?php|\\?>", "");
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    private String readByProcessBuilder(Vector<String> cmd, boolean checkHeader) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        processBuilder.redirectErrorStream(true);
        Process process =  processBuilder.start();

        StringBuilder bld = new StringBuilder();

        try (BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            if (checkHeader) {
                while ((line = read.readLine()) != null) {
                    if (line.trim().startsWith("Location") || line.trim().startsWith("location")) {
                        return line;
                    }
                }
                throw new Exception("Aucune Location trouvée");
            } else {
                while ((line = read.readLine()) != null) {
                    bld.append(line + "\n");
                }
                return bld.toString();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // $ Fonction tsotra
    
    public String giveDataPhp(String methode, String postData) throws Exception {
        String[] splt = path.split("\\?");
        Vector<String> cmd = new Vector<>();
        cmd.add("php");
        cmd.add("-r");

        boolean redirection = false;

        String ifGetOrPost = "";

        String redirect = null;

        if ((redirect = checkHeader(splt[0])) != null) {
            redirection = !redirection;
            ifGetOrPost =  redirect;
        } else {
            ifGetOrPost = "include('" + splt[0] + "')";
        }

        if (methode.equals("GET")) {
            String data = "";
            if (splt.length == 2) {
                data = splt[1];
            }
            cmd.add("if (session_id('sess') == null) { session_id('sess'); } parse_str('" + data + "', $_GET); " + ifGetOrPost + ";");
        } else if (methode.equals("POST")) {
            cmd.add("if (session_id('sess') == null) { session_id('sess'); } parse_str('" + postData + "', $_POST); " + ifGetOrPost + ";"); 
        }

        if (redirection) {
            return ResponseHTTP.response200("", getRealPath(), readByProcessBuilder(cmd, true));
        }

        return ResponseHTTP.response200(readByProcessBuilder(cmd, false), getRealPath());
    }

}