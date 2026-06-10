public abstract class PotionDecorator implements Potion {
    protected final Potion potion;

    protected PotionDecorator(Potion potion) {
        this.potion = potion;
    }

    @Override
    public int getPV() {
        return potion.getPV();
    }

    @Override
    public String getEffets() {
        return potion.getEffets();
    }
}
