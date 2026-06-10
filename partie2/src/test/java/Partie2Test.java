import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class Partie2Test {
    @Test
    void testPaireAndOutilsGeneriques() {
        Paire<String, Integer> paire = new Paire<>("A", 1);
        assertEquals("A", paire.getFirst());
        assertEquals(1, paire.getSecond());
        assertEquals("Paire{first=A, second=1}", paire.toString());

        Paire<Integer, String> swapped = paire.swap();
        assertEquals(1, swapped.getFirst());
        assertEquals("A", swapped.getSecond());

        assertEquals(5, OutilsGeneriques.max(List.of(1, 5, 3)));
        assertEquals("a,b,c", OutilsGeneriques.concat(List.of("a", "b", "c"), ","));
    }

    @Test
    void testLambdasAndStreams() {
        assertEquals(">> HELLO", LambdaFactory.majusculeEtPrefixe().appliquer("hello"));
        assertEquals(120, LambdaFactory.factorielle().appliquer(5));
        assertEquals(8, LambdaFactory.fibonacci().apply(6));

        List<Produit> produits = List.of(
                new Produit("1", "Livre", 10.0, "loisir", 3),
                new Produit("2", "Stylo", 2.0, "bureau", 0),
                new Produit("3", "Jeu", 60.0, "loisir", 1)
        );
        BoutiqueService service = new BoutiqueService(produits);
        assertEquals(2, service.getProduitsDisponibles().size());
        assertEquals(35.0, service.getPrixMoyen("loisir"));
        Map<String, List<String>> noms = service.getNomsParCategorie();
        assertTrue(noms.get("loisir").contains("Jeu"));
        assertEquals(1L, service.getAlertesRupture(2).get("loisir"));
        Optional<Produit> cher = service.getProduitLePlusCher();
        assertTrue(cher.isPresent());
        assertEquals(90.0, service.getValeurTotaleStock());
    }
}
