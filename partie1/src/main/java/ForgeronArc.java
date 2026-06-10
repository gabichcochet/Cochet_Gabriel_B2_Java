public class ForgeronArc extends Forgeron {
    @Override
    public Arme forger() {
        return new Arc();
    }

    private static class Arc implements Arme {
        @Override
        public String nom() {
            return "Arc";
        }

        @Override
        public int degats() {
            return 60;
        }
    }
}
