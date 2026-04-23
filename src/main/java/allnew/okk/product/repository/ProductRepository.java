package allnew.okk.product.repository;

import allnew.okk.product.interpreter.ProductSearchExpression;
import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.exception.ProductExceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Week 2, Pattern Singleton 2, jakub marciniuk
// repozytorium produktów, implementujące wzorzec singleton, przechowujące produkty w pamięci
public class ProductRepository extends AbstractProductRepository {
    // Singleton - prywatna statyczna instancja klasy oraz prywatny konstruktor
    private final static ProductRepository instance = new ProductRepository();
    private final Map<String, BaseProduct> products = new HashMap<>();

    // Week 7, Single Responsibility Principle, jakub marciniuk
    // oddzielenie odpowiedzialności za generowanie ID do osobnej klasy
    private final IdGenerator idGenerator = new SequenceIdGenerator("OKK-");
    // End Week 7, Single Responsibility Principle, jakub marciniuk
    private ProductRepository(){}

    public static synchronized ProductRepository getInstance() {
        return instance;
    }

    @Override
    protected String generateNewId() {
        return idGenerator.generateId();
    }

    @Override
    public void addProduct(BaseProduct product) {
        // Week 7, open-closed principle (OCP) , użycie interfejsu i implementacji, jakub marciniuk
        String uniqueId = generateNewId();
        // end Week 7, open-closed principle (OCP), jakub marciniuk
        products.put(uniqueId, product);
    }

    @Override
    // Week 9, custom exceptions, jakub marciniuk
    public BaseProduct getProduct(String identifier) {
        BaseProduct foundProduct = products.get(identifier);
        if (foundProduct == null) {
            throw new ProductNotFoundException("Nie znaleziono produktu o ID: " + identifier);
        }
        return foundProduct;
    }
    // End Week 9, custom exceptions, jakub marciniuk

    public String getIdByProduct(BaseProduct product) {
        for (Map.Entry<String, BaseProduct> entry : products.entrySet()) {
            if (entry.getValue().equals(product)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public List<BaseProduct> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public boolean removeProduct(String identifier) {
        validateProductExistence(identifier);
        products.remove(identifier);
        return true;
    }

    // Week 9, custom exceptions, jakub marciniuk
    private void validateProductExistence(String identifier) {
        if (!products.containsKey(identifier)) {
            throw new ProductRemovalException("Błąd usuwania. Brak ID: " + identifier);
        }
    }
    // End Week 9, custom exceptions, jakub marciniuk

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
    // end, week 5, pattern interpreter 5, jakub marciniuk

}
// End, Week 2, Pattern Singleton 2, jakub marciniuk
