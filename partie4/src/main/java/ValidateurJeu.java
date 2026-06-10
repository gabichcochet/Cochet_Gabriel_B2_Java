import java.util.Map;
import java.util.regex.Pattern;

public class ValidateurJeu {
    private static final Pattern PSEUDO_PATTERN = Pattern.compile("^[A-Za-z0-9_]{3,16}$");
    private static final Pattern SCORE_PATTERN = Pattern.compile("([A-Za-z0-9_]+):(\\d+)");
    private static final Pattern NUMERIC_BLOCK_PATTERN = Pattern.compile("\\d+");

    public static boolean pseudoValide(String pseudo) {
        return RegexUtils.valide(pseudo, PSEUDO_PATTERN);
    }

    public static Map<String, Integer> extraireScores(String texte) {
        return RegexUtils.extraireScores(texte, SCORE_PATTERN);
    }

    public static String masquerNombres(String texte) {
        return RegexUtils.remplaceTout(texte, NUMERIC_BLOCK_PATTERN, "***");
    }
}
