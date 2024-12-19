package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogHandler {

    private File accsFl = new File("../../log/acces.log");
    private File errFile = new File("../../log/err.log");

    private String omeoIp(String host)  {
        if (host.equals("0:0:0:0:0:0:0:1")) {
            host = "127.0.0.1";
        }
        return host;
    }

    private String omeoDaty() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public void accesLog(String host, String head) {

        host = omeoIp(host);
        try (FileWriter wrt = new FileWriter(accsFl, true);
            BufferedWriter write = new BufferedWriter(wrt)) {
            String line = omeoDaty() + " [" + host + "] [" + head + "]\n";
            write.write(line);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void errLog(String host ,String err) {
        host = omeoIp(host);
        try (FileWriter wrt = new FileWriter(errFile, true);
            BufferedWriter write = new BufferedWriter(wrt)) {
            String line = omeoDaty() + " [" + host + "] [" + err + "]\n";
            write.write(line);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 