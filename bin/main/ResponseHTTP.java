package server;

import other.MimeType;

public class ResponseHTTP {

    /**
     * Reponse http izay avaly anleh navigateur rah ohatra ka oe ok leh izi
     *      Otranzai daoly leh reponse rehetra eo ambany reo fa meme principe ian
     * @param body ilay corps omena anilay reponse
     * @param path ilay angalana content-type amin'ny alalan'ny class MimeType
     * @return mamerina anilay reponse http
     * @throws Exception
     */
    public static String response200(String body, String path) throws Exception {
        MimeType mime = new MimeType();
        String entete = "HTTP/1.1 200 OK\n" + //
                        "Content-Type: " + mime.getContentType(path) + "; charset=UTF-8\n" + //
                        "Content-Length: " + body.length() + "\n\n";
                
        return entete + body;
    }

    public static String response200(String body) throws Exception {
        String entete = "HTTP/1.1 200 OK\n" + //
                        "Content-Type: text/html; charset=UTF-8\n" + //
                        "Content-Length: " + body.length() + "\n\n";

        return entete + body;
    }

    /**
     * Ito sy ilay error500 no mila azavaiko kely
     *     io dia mamerina reponse 404 raha toa ka tsy miexiste ilay fichier na dossier tina jerena ny contenuany
     *     otranzai ian leh erreur 500 io fa io ndray erreur rah oe au cas de probleme ilay serveur
     * @return
     */
    public static String error404() {
        String body = "<!DOCTYPE html>\n" + //
                        "   <html lang=\"en\">\n" + //
                        "   <head>\n" + //
                        "       <meta charset=\"UTF-8\">\n" + //
                        "       <title>404 Not Found</title>\n" + //
                        "   </head>\n" + //
                        "   <body>\n" + //
                        "       <h1>404 - Not Found</h1>\n" + //
                        "       <p>The requested resource could not be found on this server.</p>\n" + //
                        "   </body>\n" + //
                        "</html>\n";

        String entete = "HTTP/1.1 404 Not Found\n" + //
                        "Content-Type: text/html; charset=UTF-8\n" + //
                        "Content-Length: " + body.length() + "\n" +
                        "\n";

        

        return entete + body;
    }

    public static String error500() {
        String body = "<!DOCTYPE html>\n" + //
        "   <html lang=\"en\">\n" + //
        "   <head>\n" + //
        "       <meta charset=\"UTF-8\">\n" + //
        "       <title>500 Internal Server Error</title>\n" + //
        "   </head>\n" + //
        "   <body>\n" + //
        "       <h1>500 - Internal Server Error</h1>\n" + //
        "       <p>Something went wrong on our end. Please try again later.</p>\n" + //
        "   </body>\n" + //
        "</html>\n";

        String entete = "HTTP/1.1 500 Internal Server Error\n" + //
                        "Content-Type: text/html; charset=UTF-8\n" + //
                        "Content-Length: " + body.length() + "\n" +
                        "\n";
        return entete + body;
    }
}
