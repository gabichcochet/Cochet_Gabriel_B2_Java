public class EtatVivant extends EtatPersonnage {
    public EtatVivant(Personnage personnage) {
        super(personnage);
    }

    @Override
    public String attaquer(String cible) {
        return personnage.getNom() + " attaque " + cible + ".";
    }

    @Override
    public String recevoirPoison() {
        personnage.setEtat(new EtatEmpoisonne(personnage));
        return personnage.getNom() + " est empoisonné.";
    }

    @Override
    public String mourir() {
        personnage.setEtat(new EtatMort(personnage));
        return personnage.getNom() + " est mort.";
    }
}
