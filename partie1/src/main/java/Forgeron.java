public abstract class Forgeron {
    public abstract Arme forger();

    public final String presenterArme() {
        Arme arme = forger();
        return "Arme forgée : " + arme.nom() + " (" + arme.degats() + " dégâts)";
    }
}
