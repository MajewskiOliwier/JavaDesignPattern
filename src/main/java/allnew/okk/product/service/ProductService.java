package allnew.okk.product.service;

import allnew.okk.product.repository.ProductRepository;

// Tydzień 2, Wzorzec Singleton 3
// klasa singleton odpowiedzialna za zarządzanie produktami, w tym duplikowanie produktów
public class ProductService {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    // Singleton - jedna instancja serwisu dla całej aplikacji
    private final static ProductService instance = new ProductService();

    private ProductService(){}

    public static ProductService getInstance() {
        return instance;
    }

    // Tydzień 2, Wzorzec Prototype 4
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
    // Koniec, Tydzień 2, Wzorzec Prototype 4

}
// Koniec, Tydzień 2, Wzorzec Singleton 3