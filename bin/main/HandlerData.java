package client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import other.MimeType;
import server.ResponseHTTP;
import server.Serveur;

public class HandlerData {

    private String pathFicher;
    private OutputStream out;

    // ? Constructeur

    public HandlerData(String pathFicher) {
        this.pathFicher = pathFicher;
    }

    public HandlerData(String pathFicher, OutputStream out) {
        this.pathFicher = pathFicher;
        this.out = out;
    }

    // $ Fonction tsotra

            private boolean equalsRoot() {
                return Serveur.root.equals(pathFicher);
            }

            private boolean equalsRootWithSlash() {
                return Serveur.rootWithSlash().equals(pathFicher);
            }

        /**
         * Html avant la liste  des contenus du dossier
         * @return
         */
        public String getBefore() {
            return "<!DOCTYPE html>\n" + //
                    "<html lang=\"en\">\n" + //
                    "<head>\n" + //
                    "    <meta charset=\"UTF-8\">\n" + //
                    "   <link rel=\"stylesheet\" href=\"/ROOT/style.css\">\n"+
                    "   <link rel=\"stylesheet\" href=\"/ROOT/font/bootstrap-icons.min.css\">\n"+
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + //
                    "    <title>Document</title>\n" + //
                    "</head>\n" + //
                    "<body>\n" + //
                    "    <section>\n" + //
                    "        <header>\n" + //
                    "            <h1>Apache MV</h1>\n" + //
                    "        </header>\n" + //
                    "        <div class=\"bd\">\n" + //
                    "            <ul>";
        }

        /**
         * Html apres la liste des contenus du dossier
         * @return
         */
        public String getAfter() {
            return "            </ul>\n" + //
                    "        </div>\n" + //
                    "    </section>\n" + //
                    "</body>\n" + //
                    "</html>";
        }

        /**
         * Html anleh dossier rah dossier leh izi
         * @param path chemin misy anilay dossier izai hoanalysena
         * @return mamerina anelh html du dossier
         * @throws Exception
         */
        public String repDir(String path) throws Exception {

            File fl = new File(path);
            if (!fl.exists()) throw new Exception("Ce dossier n'existe pas");
            File[] listFile = fl.listFiles();


            StringBuilder bld = new StringBuilder();
            bld.append(getBefore());

            String dir = fl.getAbsolutePath().replaceAll(Serveur.root, "");

            if (!dir.equals("/")) {
                dir += "/";
            }

            // ! Manao liste anilay dossier oe izao avy ny ao anatiny

            for (File file : listFile) {
                String iconClass;
                String slash = "";
                if (file.isDirectory()) {
                    iconClass = "bi-folder2";
                    slash = "/";

                } else {
                    MimeType mime = new MimeType();
                    iconClass = mime.getIcone(file.getName());
                }

                if (!file.equals(new File(Serveur.rootWithSlash() + "ROOT")) && !file.getName().equalsIgnoreCase("favicon.ico") ) {
                    bld.append("                <li>\n" + //
                                "                    <i class=\"bi " + iconClass +  "\"></i>\n" + 
                                "                    <a href=\"" + dir + file.getName() + slash + "\">" + file.getName() + slash +  "</a>\n" + //
                                "                </li>");
                }
            }

            bld.append(getAfter());


            // ! manisy lien retour rah afaka anaovana retour
            if (!fl.getParent().equals(Serveur.racineDeProjet)) {
                String back = fl.getParent().replaceAll(Serveur.root, "");
                back = back.isEmpty() ? "/" : back;
                bld.append("<a href='" + back +"' id='retour'> <i class=\"bi bi-arrow-left-short\"></i> Retour</a>");
            }

            return ResponseHTTP.response200(bld.toString());
        }

    /**
     * Famakiana fichier rehetra ankoatran'ny fichier php satria efa Class hafa ny miandraikitra anizan (HandlerPhpData)
     *      Ny tombony aminito methode ito dia n'importe quel fichier dia hainy daoly ny mamaky anazy ex : image, etc
     * @param pathFicher ilay chemin anilay fichier hanaovana lecture
     * @throws Exception
     */    
    public void sendFile(String pathFicher) throws Exception {
        File file = new File(pathFicher);

        try (FileInputStream fis = new FileInputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {

            MimeType mime = new MimeType();

            String contentType = mime.getContentType(pathFicher);
            
            String headers = "HTTP/1.1 200 OK\r\n" +
                             "Content-Type: " + contentType + "\r\n" +
                             "Content-Length: " + file.length() + "\r\n" +
                             "Connection: close\r\n" +
                             "\r\n";

            bos.write(headers.getBytes());

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            bos.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Mamerina anilay reponse omena anilay client
     * @return reponse omena client
     * @throws Exception
     */
    public String manomeReponse() throws Exception {
        if (equalsRootWithSlash() || new File(pathFicher).isDirectory()) {
            return repDir(pathFicher);
        }
        sendFile(pathFicher);
        return null;
    }
}
