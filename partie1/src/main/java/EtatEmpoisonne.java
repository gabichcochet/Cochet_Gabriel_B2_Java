public class EtatEmpoisonne extends EtatPersonnage {
    public EtatEmpoisonne(Personnage personnage) {
        super(personnage);
    }

    @Override
    public String attaquer(String cible) {
        int nouveauPv = personnage.getPv() - 10;
        personnage.setPv(nouveauPv);
        return personnage.getNom() + " attaque " + cible + ". " + personnage.getNom() + " perd 10 PV (poison)";
    }

    @Override
    public String recevoirPoison() {
        return "Déjà empoisonné.";
    }

    @Override
    public String mourir() {
        personnage.setEtat(new EtatMort(personnage));
        return personnage.getNom() + " est mort.";
    }
}
