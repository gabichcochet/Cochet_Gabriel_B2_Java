import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper utilities for reusable regex operations.
 */
public final class RegexUtils {
    private RegexUtils() {
    }

    public static boolean valide(String texte, Pattern motif) {
        return texte != null && motif.matcher(texte).matches();
    }

    public static Map<String, Integer> extraireScores(String texte, Pattern motif) {
        Map<String, Integer> resultat = new HashMap<>();
        if (texte == null || texte.isBlank()) {
            return resultat;
        }
        Matcher matcher = motif.matcher(texte);
        while (matcher.find()) {
            resultat.put(matcher.group(1), Integer.parseInt(matcher.group(2)));
        }
        return resultat;
    }

    public static String remplaceTout(String texte, Pattern motif, String remplacement) {
        if (texte == null) {
            return null;
        }
        return motif.matcher(texte).replaceAll(remplacement);
    }

    public static Predicate<String> nonNullEtNonVide() {
        return valeur -> valeur != null && !valeur.isBlank();
    }
}
