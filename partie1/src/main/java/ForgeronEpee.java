public class ForgeronEpee extends Forgeron {
    @Override
    public Arme forger() {
        return new Epee();
    }

    private static class Epee implements Arme {
        @Override
        public String nom() {
            return "Epee";
        }

        @Override
        public int degats() {
            return 80;
        }
    }
}
