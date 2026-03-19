package allnew.okk.product.facade;

import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.model.ProductCategory;
import allnew.okk.product.model.ProductCondition;
import allnew.okk.product.repository.ProductRepository;

import java.util.List;

// Week 3, Pattern Facade 1 Jakub Marciniuk
// This class provides a simplified interface for searching products based on various criteria
// hiding the complexity of the underlying product repository and search logic
public class ProductSearchFacade {
    private final ProductRepository productRepository = ProductRepository.getInstance();

    public List<BaseProduct> searchProducts(ProductCategory category, ProductCondition condition, double minPrice, double maxPrice, String name) {
        return productRepository.getAllProducts().stream()
                .filter(p -> (category == null || p.getCategory() == category) &&
                             (condition == null || p.getCondition() == condition) &&
                             (minPrice <= 0 || p.getPrice() >= minPrice) &&
                             (maxPrice <= 0 || p.getPrice() <= maxPrice) &&
                             (name == null || p.getName().toLowerCase().contains(name.toLowerCase())))
                .toList();
    }

    public List<BaseProduct> searchProductsByName(String name) {
        return searchProducts(null, null, 0, 0, name);
    }

    public List<BaseProduct> searchProductsByCategory(ProductCategory category) {
        return searchProducts(category, null, 0, 0, null);
    }

    public List<BaseProduct> searchProductsByCondition(ProductCondition condition) {
        return searchProducts(null, condition, 0, 0, null);
    }

    public List<BaseProduct> searchProductsByPriceRange(double minPrice, double maxPrice) {
        return searchProducts(null, null, minPrice, maxPrice, null);
    }

    public List<BaseProduct> searchProductsCheaperThan(double maxPrice) {
        return searchProducts(null, null, 0, maxPrice, null);
    }
}
// End Week 3, Pattern Facade 1 Jakub Marciniuk