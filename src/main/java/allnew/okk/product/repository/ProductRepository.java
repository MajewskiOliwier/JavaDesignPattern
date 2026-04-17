package allnew.okk.product.repository;

import allnew.okk.product.interpreter.ProductSearchExpression;
import allnew.okk.product.model.BaseProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Week 2, Pattern Singleton 2
// repozytorium produktów, implementujące wzorzec singleton, przechowujące produkty w pamięci
public class ProductRepository{
    // Singleton - prywatna statyczna instancja klasy oraz prywatny konstruktor
    private final static ProductRepository instance = new ProductRepository();
    private final Map<String, BaseProduct> products = new HashMap<>();

    // Week 6, Single Responsibility Principle
    // oddzielenie odpowiedzialności za generowanie ID do osobnej klasy
    private IdGenerator idGenerator = new SequenceIdGenerator("OKK-");
    // Week 6, open-closed principle, użycie interfejsu i implementacji
    private ProductRepository(){}

    public static synchronized ProductRepository getInstance() {
        return instance;
    }

    public void addProduct(BaseProduct product) {
        String id = idGenerator.generateId();
        products.put(id, product);
    }

    public BaseProduct getProduct(String id) {
        return products.get(id);
    }

    public String getIdByProduct(BaseProduct product) {
        for (Map.Entry<String, BaseProduct> entry : products.entrySet()) {
            if (entry.getValue().equals(product)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public List<BaseProduct> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public boolean removeProduct(String id) {
        return products.remove(id) != null;
    }

    public void clear() {
        products.clear();
    }

    public void updateProduct(String id, BaseProduct updatedProduct) {
        if (products.containsKey(id)) {
            products.put(id, updatedProduct);
        }
    }

    // week 5, pattern interpreter 5 Jakub Marciniuk
    public List<BaseProduct> filter(ProductSearchExpression expression){
        return products.values().stream()
                .filter(expression::evaluate)
                .toList();
    }
    // end, week 5, pattern interpreter 5

}
// End, Week 2, Pattern Singleton 2
