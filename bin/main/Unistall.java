package other;

import java.io.File;

public class Unistall {

    public static void main(String[] args) {
        String[] listDir = {
            "/etc/httpServer",
            "/opt/apachemv",
            "/usr/local/bin/apachemv.sh",
            "/usr/share/httpServer",
            "/var/log/httpServer",
        };

        for (String path : listDir) {
            File fl = new File(path);
            if (fl.exists()) {
                if (deleteRecursively(fl)) {
                    System.out.println("Supprimé : " + path);
                } else {
                    System.out.println("Échec de la suppression : " + path);
                }
            } else {
                System.out.println("Le chemin n'existe pas : " + path);
            }
        }
    }

    public static boolean deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    deleteRecursively(f);
                }
            }
        }
        return file.delete();
    }
}
