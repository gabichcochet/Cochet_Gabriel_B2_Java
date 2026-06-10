import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère la lecture et l'écriture des scores en format CSV.
 */
public class ScoreManager {
    private static final String SEPARATOR = ",";

    /**
     * Écrit une liste de scores dans un fichier CSV.
     *
     * @param fichier chemin du fichier de sortie
     * @param scores  liste de paires pseudo / score
     * @throws IOException si l'écriture échoue
     */
    public static void sauvegarder(String fichier, List<Paire<String, Integer>> scores) throws IOException {
        Path path = Path.of(fichier);
        List<String> lignes = new ArrayList<>(scores.size());
        for (Paire<String, Integer> score : scores) {
            lignes.add(CsvUtils.formaterLigne(score.getFirst(), String.valueOf(score.getSecond())));
        }
        CsvUtils.ecrire(path, lignes);
    }

    /**
     * Lit un fichier CSV de scores et retourne une liste de paires.
     *
     * @param fichier chemin du fichier à lire
     * @return liste de paires pseudo / score
     * @throws IOException si la lecture échoue
     */
    public static List<Paire<String, Integer>> charger(String fichier) throws IOException {
        Path path = Path.of(fichier);
        if (!Path.of(fichier).toFile().exists()) {
            return List.of();
        }

        List<Paire<String, Integer>> resultat = new ArrayList<>();
        for (String ligne : CsvUtils.lire(path)) {
            String[] elements = ligne.split(SEPARATOR);
            if (elements.length != 2) {
                continue;
            }
            String pseudo = elements[0].trim();
            String scoreStr = elements[1].trim();
            try {
                int score = Integer.parseInt(scoreStr);
                resultat.add(new Paire<>(pseudo, score));
            } catch (NumberFormatException ignored) {
                // ignorer les lignes mal formées
            }
        }
        return resultat;
    }
}
