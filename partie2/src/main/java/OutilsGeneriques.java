import java.util.List;
import java.util.stream.Collectors;

public class OutilsGeneriques {

    public static <T extends Comparable<? super T>> T max(List<T> liste) {
        return liste.stream()
                .max(Comparable::compareTo)
                .orElseThrow(() -> new java.util.NoSuchElementException("Liste vide"));
    }

    public static <T> String concat(List<T> liste, String sep) {
        return liste.stream()
                .map(Object::toString)
                .collect(Collectors.joining(sep));
    }
}
