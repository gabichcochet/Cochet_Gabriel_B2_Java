pimport org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Partie1Test {
    @Test
    void testGameServerSingleton() {
        GameServer a = GameServer.getInstance();
        GameServer b = GameServer.getInstance();
        assertSame(a, b);
        a.connect();
        assertEquals(1, b.getConnectedPlayers());
        assertEquals(8080, a.getPort());
    }

    @Test
    void testPersonnageBuilderAndToString() {
        Personnage mage = new Personnage.Builder("Alice", "Mage")
                .pv(120)
                .mana(80)
                .arme("Bâton")
                .estElite(true)
                .build();
        assertEquals("Alice", mage.getNom());
        assertTrue(mage.toString().contains("Mage"));
        assertTrue(mage.toString().contains("Poings") || mage.toString().contains("Bâton"));
    }

    @Test
    void testForgeronAndPotionEtc() {
        Forgeron epee = new ForgeronEpee();
        assertEquals("Arme forgée : Epee (80 dégâts)", epee.presenterArme());
        Potion potion = new AvecMana(new AvecAntidote(new PotionDeBase()));
        assertEquals(80, potion.getPV());
        assertTrue(potion.getEffets().contains("Antidote"));
        assertTrue(potion.getEffets().contains("Restauration de mana"));
    }

    @Test
    void testNotificationAndState() {
        Notification notif = NotificationFactory.urgenceParEmail();
        assertDoesNotThrow(() -> notif.notifier("joueur", "Alerte"));
        Notification sms = NotificationFactory.normaleParSMS();
        assertDoesNotThrow(() -> sms.notifier("joueur", "Test"));

        Personnage guerrier = new Personnage.Builder("Léo", "Guerrier").build();
        assertEquals("Léo attaque Gobelin.", guerrier.attaquer("Gobelin"));
        assertEquals("Léo est empoisonné.", guerrier.recevoirPoison());
        assertEquals("Léo attaque Gobelin. Léo perd 10 PV (poison)", guerrier.attaquer("Gobelin"));
        assertEquals("Léo est mort.", guerrier.mourir());
        assertEquals("Action impossible, Léo est mort.", guerrier.attaquer("Gobelin"));
    }
}
