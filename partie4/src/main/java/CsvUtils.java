import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utilitaires pour lire et écrire des fichiers texte CSV simples.
 */
public final class CsvUtils {
    private CsvUtils() {
    }

    public static List<String> lire(Path fichier) throws IOException {
        return Files.readAllLines(fichier);
    }

    public static void ecrire(Path fichier, List<String> lignes) throws IOException {
        Files.write(fichier, lignes);
    }

    public static String formaterLigne(String... items) {
        return String.join(",", items);
    }
}
