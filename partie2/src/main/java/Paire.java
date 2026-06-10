public class Paire<F, S> {
    private final F first;
    private final S second;

    public Paire(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public Paire<S, F> swap() {
        return new Paire<>(second, first);
    }

    @Override
    public String toString() {
        return "Paire{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
