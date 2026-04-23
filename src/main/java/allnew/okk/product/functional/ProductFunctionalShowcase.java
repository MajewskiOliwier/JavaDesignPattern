package allnew.okk.product.functional;

import allnew.okk.product.model.BaseProduct;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Week 10, Functional Programming Showcase, Jakub Marciniuk
public class ProductFunctionalShowcase {

    // 3 interfejsy funkcyjne
    @FunctionalInterface
    public interface ProductFilter { boolean matches(BaseProduct product); }

    @FunctionalInterface
    public interface PriceMutator { double applyDiscount(double currentPrice); }

    @FunctionalInterface
    public interface NotificationAction { void send(BaseProduct product, String message); }

    public void demonstrateLambdas(BaseProduct testProduct) {
        ProductFilter affordableFilter = p -> p.getPrice() < 100.0;
        PriceMutator blackFridayDiscount = price -> price * 0.5;
        NotificationAction emailNotify = (p, msg) -> System.out.println("Powiadomienie: " + p.getName() + " - " + msg);

        if (affordableFilter.matches(testProduct)) {
            emailNotify.send(testProduct, "Produkt został przeceniony: " + blackFridayDiscount.applyDiscount(testProduct.getPrice()));
        }
    }

    // Przetwarzanie 3 kolekcji (Streamy)
    public List<BaseProduct> processAvailableProducts(List<BaseProduct> allProducts) {
        return allProducts.stream().filter(BaseProduct::canBePurchased).collect(Collectors.toList());
    }

    public List<String> extractProductNames(List<BaseProduct> allProducts) {
        return allProducts.stream().map(BaseProduct::getName).collect(Collectors.toList());
    }

    public double calculateTotalInventoryValue(List<BaseProduct> allProducts) {
        return allProducts.stream().mapToDouble(BaseProduct::getPrice).sum();
    }

    // Predicate oraz Function
    public List<BaseProduct> findUsingPredicate(List<BaseProduct> products, Predicate<BaseProduct> condition) {
        return products.stream().filter(condition).collect(Collectors.toList());
    }

    public List<Double> mapUsingFunction(List<BaseProduct> products, Function<BaseProduct, Double> mapper) {
        return products.stream().map(mapper).collect(Collectors.toList());
    }
}
// End Week 10, Functional Programming Showcase, Jakub Marciniuk