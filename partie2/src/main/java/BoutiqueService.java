import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoutiqueService {
    private final List<Produit> produits;

    public BoutiqueService(List<Produit> produits) {
        this.produits = List.copyOf(produits);
    }

    public List<Produit> getProduitsDisponibles() {
        return produits.stream()
                .filter(produit -> produit.stock() > 0)
                .sorted(Comparator.comparingDouble(Produit::prix)
                        .thenComparing(Produit::nom))
                .toList();
    }

    public double getPrixMoyen(String categorie) {
        return produits.stream()
                .filter(produit -> produit.categorie().equals(categorie))
                .mapToDouble(Produit::prix)
                .average()
                .orElse(0.0);
    }

    public Map<String, List<String>> getNomsParCategorie() {
        return produits.stream()
                .collect(Collectors.groupingBy(
                        Produit::categorie,
                        Collectors.mapping(Produit::nom,
                                Collectors.collectingAndThen(
                                        Collectors.toCollection(ArrayList::new), liste -> {
                                            liste.sort(String::compareTo);
                                            return liste;
                                        }))
                ));
    }

    public Map<String, Long> getAlertesRupture(int seuil) {
        return produits.stream()
                .filter(produit -> produit.stock() < seuil)
                .collect(Collectors.groupingBy(
                        Produit::categorie,
                        Collectors.counting()
                ));
    }

    public Optional<Produit> getProduitLePlusCher() {
        return produits.stream()
                .max(Comparator.comparingDouble(Produit::prix));
    }

    public double getValeurTotaleStock() {
        return produits.stream()
                .mapToDouble(produit -> produit.prix() * produit.stock())
                .sum();
    }
}
