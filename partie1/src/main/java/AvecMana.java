public class AvecMana extends PotionDecorator {
    public AvecMana(Potion potion) {
        super(potion);
    }

    @Override
    public int getPV() {
        return potion.getPV() + 30;
    }

    @Override
    public String getEffets() {
        return potion.getEffets() + ", Restauration de mana";
    }
}
