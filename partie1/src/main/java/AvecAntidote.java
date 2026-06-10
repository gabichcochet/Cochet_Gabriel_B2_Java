public class AvecAntidote extends PotionDecorator {
    public AvecAntidote(Potion potion) {
        super(potion);
    }

    @Override
    public int getPV() {
        return potion.getPV();
    }

    @Override
    public String getEffets() {
        return potion.getEffets() + ", Antidote";
    }
}
