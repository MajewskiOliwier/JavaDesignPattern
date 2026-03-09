package allnew.okk.product.repository;

import allnew.okk.product.model.BaseProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// repozytorium produktów, implementujące wzorzec singleton, przechowujące produkty w pamięci
public class ProductRepository{
    private final static ProductRepository instance = new ProductRepository();
    private final Map<String, BaseProduct> products = new HashMap<>();
    private int nextId = 1;
    private ProductRepository(){}

    public static synchronized ProductRepository getInstance() {
        return instance;
    }

    public void addProduct(BaseProduct product) {
        String id = "OKK-" + nextId++;
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
        nextId = 1;
    }

    public void updateProduct(String id, BaseProduct updatedProduct) {
        if (products.containsKey(id)) {
            products.put(id, updatedProduct);
        }
    }


}
