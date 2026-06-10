import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class Partie4Test {
    @Test
    void testValidateurJeu() {
        assertTrue(ValidateurJeu.pseudoValide("Alice_01"));
        assertFalse(ValidateurJeu.pseudoValide("ab"));
        assertFalse(ValidateurJeu.pseudoValide("invalid$"));

        Map<String, Integer> scores = ValidateurJeu.extraireScores("Alice:1500 points, Bob:320 points");
        assertEquals(1500, scores.get("Alice"));
        assertEquals(320, scores.get("Bob"));
        assertEquals("Code *** et pin ***", ValidateurJeu.masquerNombres("Code 1234 et pin 5678"));
    }

    @Test
    void testScoreManagerAndController() throws IOException {
        Path tempFile = Files.createTempFile("scores", ".csv");
        try {
            List<Paire<String, Integer>> entries = List.of(
                    new Paire<>("Alice", 1500),
                    new Paire<>("Bob", 320)
            );
            ScoreManager.sauvegarder(tempFile.toString(), entries);
            List<Paire<String, Integer>> loaded = ScoreManager.charger(tempFile.toString());
            assertEquals(2, loaded.size());
            assertEquals("Alice", loaded.get(0).getFirst());

            // Un fichier mal formé ne doit pas planter la lecture
            Files.writeString(tempFile, "Alice,1500\nmalformed\nBob,320\n");
            List<Paire<String, Integer>> withMalformed = ScoreManager.charger(tempFile.toString());
            assertEquals(2, withMalformed.size());
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void testRegexUtilsDirectement() {
        assertTrue(RegexUtils.valide("Alice_01", Pattern.compile("^[A-Za-z0-9_]{3,16}$")));
        String original = "Marie:2400 points, Luc:1200 points";
        assertEquals(2, RegexUtils.extraireScores(original, Pattern.compile("([A-Za-z0-9_]+):(\\d+)")) .size());
        assertEquals("Code ***, pin ***", RegexUtils.remplaceTout("Code 123, pin 456", Pattern.compile("\\d+"), "***"));
    }
}
