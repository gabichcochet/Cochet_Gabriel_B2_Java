public abstract class EtatPersonnage {
    protected final Personnage personnage;

    protected EtatPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    public abstract String attaquer(String cible);
    public abstract String recevoirPoison();
    public abstract String mourir();
}
