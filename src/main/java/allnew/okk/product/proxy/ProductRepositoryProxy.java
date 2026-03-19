package allnew.okk.product.proxy;

import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.repository.ProductRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

// Week 3, Pattern Proxy 1 Jakub Marciniuk
// This class serves as a proxy to the ProductRepository, adding caching functionality to improve performance
public class ProductRepositoryProxy {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    private List<BaseProduct> cachedProducts = null;

    public List<BaseProduct> getAllProducts() {
        if (cachedProducts == null) {
            cachedProducts = productRepository.getAllProducts();
        }
        return cachedProducts;
    }

    public String getIdByProduct(BaseProduct product) {
        return productRepository.getIdByProduct(product);
    }

    public void invalidateCache() {
        cachedProducts = null;
    }

    public BaseProduct getProduct(String id) {
        return productRepository.getProduct(id);
    }

    public void addProduct(BaseProduct product) {
        productRepository.addProduct(product);
        invalidateCache();
    }

    public void clearProducts() {
        productRepository.clear();
        invalidateCache();
    }

    public boolean removeProduct(String id) {
        boolean removed = productRepository.removeProduct(id);
        if (removed) {
            invalidateCache();
        }
        return removed;
    }
}
// End Week 3, Pattern Proxy 1 Jakub Marciniuk
