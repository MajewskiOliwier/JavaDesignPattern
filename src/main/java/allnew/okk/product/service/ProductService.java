package allnew.okk.product.service;

import allnew.okk.product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

// Week 2, Pattern Singleton 3
// klasa singleton odpowiedzialna za zarządzanie produktami, w tym duplikowanie produktów
public class ProductService {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    // Singleton - jedna instancja serwisu dla całej aplikacji
    private final static ProductService instance = new ProductService();



    private ProductService(){
    }

    public static ProductService getInstance() {
        return instance;
    }

    // Week 2, Pattern Prototype 4

    public void duplicateProduct(String productId, DuplicationModifier modifier) throws CloneNotSupportedException {
        var originalProduct = productRepository.getProduct(productId);
        if (originalProduct != null) {
            try {
                var duplicatedProduct = originalProduct.clone();
                // Week 7, Single Responsibility Principle (SRP), implementation, jakub marciniuk
                // oddzielenie odpowiedzialności za modyfikację produktu klasy implementującej DuplicationModifier
                // week 7, open-closed principle (OCP), Jakub Marciniuk
                // użycie interfejsu do modyfikacji produktu podczas duplikacji
                if(modifier != null) {
                    modifier.modify(duplicatedProduct);
                }
                // end week 7, open-closed principle (OCP), jakub marciniuk/
                // end week 7, single responsibility principle (SRP), implementation, jakub marciniuk
                //duplicatedProduct.setName(originalProduct.getName() + " (Copy)");
                productRepository.addProduct(duplicatedProduct);
            }
            catch (CloneNotSupportedException e) {
                throw new CloneNotSupportedException("Produktu nie udalo sie zduplikowac: " + e.getMessage());
            }
        }
    }
    // End Week 2, Pattern Prototype 4

}
// End Week 2, Pattern Singleton 3