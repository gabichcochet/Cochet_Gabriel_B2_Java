import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Inspecteur {

    public static String getNomTable(Class<?> clazz) {
        Entite annotation = clazz.getAnnotation(Entite.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Classe non annotée avec @Entite");
        }
        return annotation.table();
    }

    public static List<String> getColonnes(Class<?> clazz) {
        return getColonneFields(clazz).stream()
                .map(field -> {
                    Colonne colonne = field.getAnnotation(Colonne.class);
                    return colonne.nom().isBlank() ? field.getName() : colonne.nom();
                })
                .collect(Collectors.toList());
    }

    public static List<String> getColonnesObligatoires(Class<?> clazz) {
        return getColonneFields(clazz).stream()
                .filter(field -> !field.getAnnotation(Colonne.class).nullable())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getMethodesLoggables(Class<?> clazz, String niveau) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Loggable.class))
                .filter(method -> method.getAnnotation(Loggable.class).niveau().equals(niveau))
                .map(Method::getName)
                .collect(Collectors.toList());
    }

    private static List<Field> getColonneFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Colonne.class))
                .collect(Collectors.toList());
    }
}
