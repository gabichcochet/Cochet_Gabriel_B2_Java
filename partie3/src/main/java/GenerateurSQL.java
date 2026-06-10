import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateurSQL {

    public static String genererSelect(Class<?> clazz) {
        Entite entite = clazz.getAnnotation(Entite.class);
        if (entite == null) {
            throw new IllegalArgumentException("Classe sans annotation @Entite");
        }
        String colonnes = getColonneFields(clazz).stream()
                .map(GenerateurSQL::nomColonne)
                .collect(Collectors.joining(", "));
        return String.format("SELECT %s FROM %s", colonnes, entite.table());
    }

    public static String genererInsert(Object objet) throws IllegalStateException, IllegalAccessException {
        Class<?> clazz = objet.getClass();
        Entite entite = clazz.getAnnotation(Entite.class);
        if (entite == null) {
            throw new IllegalArgumentException("Classe sans annotation @Entite");
        }

        List<Field> colonnes = getColonneFields(clazz);
        String noms = colonnes.stream()
                .map(GenerateurSQL::nomColonne)
                .collect(Collectors.joining(", "));

        String valeurs = colonnes.stream()
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        Object valeur = field.get(objet);
                        Colonne meta = field.getAnnotation(Colonne.class);
                        if (valeur == null) {
                            if (!meta.nullable()) {
                                throw new IllegalStateException("Champ non-nullable null: " + field.getName());
                            }
                            return "NULL";
                        }
                        return valeur instanceof String ? "'" + valeur + "'" : valeur.toString();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(", "));

        return String.format("INSERT INTO %s (%s) VALUES (%s)", entite.table(), noms, valeurs);
    }

    private static List<Field> getColonneFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Colonne.class))
                .collect(Collectors.toList());
    }

    private static String nomColonne(Field field) {
        Colonne annotation = field.getAnnotation(Colonne.class);
        return annotation.nom().isBlank() ? field.getName() : annotation.nom();
    }
}
