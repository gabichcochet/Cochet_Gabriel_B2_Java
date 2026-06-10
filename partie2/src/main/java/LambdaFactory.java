import java.util.function.Function;

public class LambdaFactory {

    public static Transformation<String> majusculeEtPrefixe() {
        return valeur -> ">> " + valeur.toUpperCase();
    }

    public static Transformation<Integer> factorielle() {
        return valeur -> {
            if (valeur == null || valeur < 0) {
                throw new IllegalArgumentException("Valeur négative");
            }
            int resultat = 1;
            for (int i = 2; i <= valeur; i++) {
                resultat *= i;
            }
            return resultat;
        };
    }

    public static Function<Integer, Integer> fibonacci() {
        return n -> {
            if (n == null || n < 0) {
                throw new IllegalArgumentException("Indice négatif");
            }
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            int a = 0;
            int b = 1;
            for (int i = 2; i <= n; i++) {
                int next = a + b;
                a = b;
                b = next;
            }
            return b;
        };
    }
}
