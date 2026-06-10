public class Personnage {
    private final String nom;
    private final String classe;
    private int pv;
    private int mana;
    private String arme;
    private boolean estElite;
    private EtatPersonnage etat;

    private Personnage(Builder builder) {
        this.nom = builder.nom;
        this.classe = builder.classe;
        this.pv = builder.pv;
        this.mana = builder.mana;
        this.arme = builder.arme;
        this.estElite = builder.estElite;
        this.etat = new EtatVivant(this);
    }

    public String getNom() {
        return nom;
    }

    public String getClasse() {
        return classe;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getMana() {
        return mana;
    }

    public String getArme() {
        return arme;
    }

    public boolean isEstElite() {
        return estElite;
    }

    public void setEtat(EtatPersonnage etat) {
        this.etat = etat;
    }

    public String attaquer(String cible) {
        return etat.attaquer(cible);
    }

    public String recevoirPoison() {
        return etat.recevoirPoison();
    }

    public String mourir() {
        return etat.mourir();
    }

    @Override
    public String toString() {
        return new StringBuilder("Personnage{")
                .append("nom='").append(nom).append("'")
                .append(", classe='").append(classe).append("'")
                .append(", pv=").append(pv)
                .append(", mana=").append(mana)
                .append(", arme='").append(arme).append("'")
                .append(", estElite=").append(estElite)
                .append('}').toString();
    }

    public static class Builder {
        private final String nom;
        private final String classe;
        private int pv = 100;
        private int mana = 50;
        private String arme = "Poings";
        private boolean estElite = false;

        public Builder(String nom, String classe) {
            this.nom = nom;
            this.classe = classe;
        }

        public Builder pv(int pv) {
            this.pv = pv;
            return this;
        }

        public Builder mana(int mana) {
            this.mana = mana;
            return this;
        }

        public Builder arme(String arme) {
            this.arme = arme;
            return this;
        }

        public Builder estElite(boolean estElite) {
            this.estElite = estElite;
            return this;
        }

        public Personnage build() {
            if (nom == null || nom.isBlank()) {
                throw new IllegalArgumentException("Le nom doit être renseigné");
            }
            if (classe == null || classe.isBlank()) {
                throw new IllegalArgumentException("La classe doit être renseignée");
            }
            return new Personnage(this);
        }
    }
}
