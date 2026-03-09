package allnew.okk.product.service;

import allnew.okk.product.repository.ProductRepository;

// klasa singleton odpowiedzialna za zarządzanie produktami, w tym duplikowanie produktów
public class ProductService {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    private final static ProductService instance = new ProductService();

    private ProductService(){}

    public static ProductService getInstance() {
        return instance;
    }

    public void duplicateProduct(String productId) throws CloneNotSupportedException {
        var originalProduct = productRepository.getProduct(productId);
        if (originalProduct != null) {
            try {
                var duplicatedProduct = originalProduct.clone();

                duplicatedProduct.setName(originalProduct.getName() + " (Copy)");
                productRepository.addProduct(duplicatedProduct);
            }
            catch (CloneNotSupportedException e) {
                throw new CloneNotSupportedException("Produktu nie udalo sie zduplikowac: " + e.getMessage());
            }
        }
    }

}
