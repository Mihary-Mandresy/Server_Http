package other;

import java.util.HashMap;

public class MimeType {

    private final HashMap<String, String> mimeTypes = new HashMap<>();
    private final HashMap<String, String> bootstrapIcons = new HashMap<>();

    /**
     * Mamerina icone na content-type :
     * io zany otran oe rah fichier .html ohatra dia text/html no mifanaraka aminy, rah image dia /image/jpeg ohatra
     * dia otranzan kou leh resaka icone io oe rah fichier html dia otranzao ny icone bootstrap izay mifandraiky aminy
     */
    public MimeType() {
        // Types MIME
        mimeTypes.put(".html", "text/html");
        mimeTypes.put(".php", "text/html");
        mimeTypes.put(".htm", "text/html");
        mimeTypes.put(".txt", "text/plain");
        mimeTypes.put(".css", "text/css");
        mimeTypes.put(".csv", "text/csv");
        mimeTypes.put(".xml", "application/xml");

        mimeTypes.put(".js", "application/javascript");
        mimeTypes.put(".mjs", "application/javascript");

        mimeTypes.put(".json", "application/json");
        mimeTypes.put(".geojson", "application/geo+json");
        mimeTypes.put(".ndjson", "application/x-ndjson");

        mimeTypes.put(".png", "image/png");
        mimeTypes.put(".jpg", "image/jpeg");
        mimeTypes.put(".jpeg", "image/jpeg");
        mimeTypes.put(".gif", "image/gif");
        mimeTypes.put(".bmp", "image/bmp");
        mimeTypes.put(".webp", "image/webp");
        mimeTypes.put(".svg", "image/svg+xml");
        mimeTypes.put(".ico", "image/vnd.microsoft.icon");

        mimeTypes.put(".mp4", "video/mp4");
        mimeTypes.put(".mpeg", "video/mpeg");
        mimeTypes.put(".webm", "video/webm");
        mimeTypes.put(".ogg", "video/ogg");
        mimeTypes.put(".avi", "video/x-msvideo");

        mimeTypes.put(".mp3", "audio/mpeg");
        mimeTypes.put(".ogg", "audio/ogg");
        mimeTypes.put(".wav", "audio/wav");
        mimeTypes.put(".weba", "audio/webm");

        mimeTypes.put(".pdf", "application/pdf");
        mimeTypes.put(".doc", "application/msword");
        mimeTypes.put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        mimeTypes.put(".xls", "application/vnd.ms-excel");
        mimeTypes.put(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        mimeTypes.put(".ppt", "application/vnd.ms-powerpoint");
        mimeTypes.put(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");

        mimeTypes.put(".zip", "application/zip");
        mimeTypes.put(".rar", "application/vnd.rar");
        mimeTypes.put(".7z", "application/x-7z-compressed");
        mimeTypes.put(".tar", "application/x-tar");
        mimeTypes.put(".gz", "application/gzip");

        mimeTypes.put(".exe", "application/octet-stream");
        mimeTypes.put(".bin", "application/octet-stream");
        mimeTypes.put(".iso", "application/x-iso9660-image");

        mimeTypes.put(".eot", "application/vnd.ms-fontobject");
        mimeTypes.put(".ttf", "font/ttf");
        mimeTypes.put(".woff", "font/woff");
        mimeTypes.put(".woff2", "font/woff2");

        // Icônes Bootstrap associées
        bootstrapIcons.put(".html", "bi-file-earmark-code");
        bootstrapIcons.put(".php", "bi-file-earmark-code");
        bootstrapIcons.put(".htm", "bi-file-earmark-code");
        bootstrapIcons.put(".txt", "bi-file-earmark-text");
        bootstrapIcons.put(".css", "bi-file-earmark-text");
        bootstrapIcons.put(".csv", "bi-file-earmark-spreadsheet");
        bootstrapIcons.put(".xml", "bi-file-earmark-code");

        bootstrapIcons.put(".js", "bi-file-earmark-code");
        bootstrapIcons.put(".mjs", "bi-file-earmark-code");

        bootstrapIcons.put(".json", "bi-file-earmark-text");
        bootstrapIcons.put(".geojson", "bi-globe");
        bootstrapIcons.put(".ndjson", "bi-file-earmark-text");

        bootstrapIcons.put(".png", "bi-file-earmark-image");
        bootstrapIcons.put(".jpg", "bi-file-earmark-image");
        bootstrapIcons.put(".jpeg", "bi-file-earmark-image");
        bootstrapIcons.put(".gif", "bi-file-earmark-image");
        bootstrapIcons.put(".bmp", "bi-file-earmark-image");
        bootstrapIcons.put(".webp", "bi-file-earmark-image");
        bootstrapIcons.put(".svg", "bi-file-earmark-image");
        bootstrapIcons.put(".ico", "bi-file-earmark-image");

        bootstrapIcons.put(".mp4", "bi-file-earmark-play");
        bootstrapIcons.put(".mpeg", "bi-file-earmark-play");
        bootstrapIcons.put(".webm", "bi-file-earmark-play");
        bootstrapIcons.put(".ogg", "bi-file-earmark-play");
        bootstrapIcons.put(".avi", "bi-file-earmark-play");

        bootstrapIcons.put(".mp3", "bi-file-earmark-music");
        bootstrapIcons.put(".wav", "bi-file-earmark-music");
        bootstrapIcons.put(".weba", "bi-file-earmark-music");

        bootstrapIcons.put(".pdf", "bi-file-earmark-pdf");
        bootstrapIcons.put(".doc", "bi-file-earmark-word");
        bootstrapIcons.put(".docx", "bi-file-earmark-word");
        bootstrapIcons.put(".xls", "bi-file-earmark-excel");
        bootstrapIcons.put(".xlsx", "bi-file-earmark-excel");
        bootstrapIcons.put(".ppt", "bi-file-earmark-ppt");
        bootstrapIcons.put(".pptx", "bi-file-earmark-ppt");

        bootstrapIcons.put(".zip", "bi-file-earmark-zip");
        bootstrapIcons.put(".rar", "bi-file-earmark-zip");
        bootstrapIcons.put(".7z", "bi-file-earmark-zip");
        bootstrapIcons.put(".tar", "bi-file-earmark-zip");
        bootstrapIcons.put(".gz", "bi-file-earmark-zip");

        bootstrapIcons.put(".exe", "bi-cpu");
        bootstrapIcons.put(".bin", "bi-cpu");
        bootstrapIcons.put(".iso", "bi-disc");

        bootstrapIcons.put(".eot", "bi-type");
        bootstrapIcons.put(".ttf", "bi-type");
        bootstrapIcons.put(".woff", "bi-type");
        bootstrapIcons.put(".woff2", "bi-type");
    }

    public String getBootstrapIcon(String extension) {
        return bootstrapIcons.getOrDefault(extension, "bi-file-earmark");
    }

    public String getIcone(String path) {
        String[] splt = path.split("/");
        String lastPath = splt[splt.length - 1];

        if (lastPath.split("\\.").length <= 1) {
            return getBootstrapIcon("");
        }

        int nbSplit = lastPath.split("\\.").length;
        String extension = "." + lastPath.split("\\.")[nbSplit - 1];
        return getBootstrapIcon(extension);
    }

    /**
     * Maka anleh content type izay mifandraiky amleh situation
     * @param path ilay string hiverifiena azy oe de type inona ilay izy
     * @return mamerina anleh content-type
     * @throws Exception
     */
    public String getContentType(String path) throws Exception {
        String[] splt = path.split("/");
        String lastPath = splt[splt.length - 1];

        if (lastPath.split("\\.").length <= 1) {
            return mimeTypes.get(".html");
        }

        int nbSplit = lastPath.split("\\.").length;
        String extension = "." + lastPath.split("\\.")[nbSplit - 1];

        if (mimeTypes.containsKey(extension)) {
            return mimeTypes.get(extension);
        } else {
            throw new Exception("Cette extension n'existe pas");
        }
    }
}
