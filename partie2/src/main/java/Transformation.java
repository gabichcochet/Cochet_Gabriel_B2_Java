@FunctionalInterface
public interface Transformation<T> {
    T appliquer(T valeur);
}
