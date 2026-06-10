import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Entite(table = "joueurs")
class JoueurTest {
    @Colonne(nom = "pseudo", nullable = false)
    private String pseudo = "Alice";

    @Colonne(nom = "score")
    private int score = 1500;

    private String motDePasseHash = "secret";

    @Loggable(niveau = "WARN")
    public void resetScore() {
        this.score = 0;
    }

    public String getPseudo() {
        return pseudo;
    }
}

public class Partie3Test {
    @Test
    void testInspecteurEtGenerateurSQL() throws IllegalAccessException {
        assertEquals("joueurs", Inspecteur.getNomTable(JoueurTest.class));
        assertEquals(List.of("pseudo", "score"), Inspecteur.getColonnes(JoueurTest.class));
        assertEquals(List.of("pseudo"), Inspecteur.getColonnesObligatoires(JoueurTest.class));
        assertEquals(List.of("resetScore"), Inspecteur.getMethodesLoggables(JoueurTest.class, "WARN"));

        assertEquals("SELECT pseudo, score FROM joueurs", GenerateurSQL.genererSelect(JoueurTest.class));
        JoueurTest joueur = new JoueurTest();
        String insert = GenerateurSQL.genererInsert(joueur);
        assertTrue(insert.contains("INSERT INTO joueurs"));
        assertTrue(insert.contains("'Alice'"));
    }
}
