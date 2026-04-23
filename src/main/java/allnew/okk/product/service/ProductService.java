package allnew.okk.product.service;

import allnew.okk.product.repository.ProductRepository;
import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.exception.ProductExceptions.*;

// Week 2, Pattern Singleton 3
// klasa singleton odpowiedzialna za zarządzanie produktami, w tym duplikowanie produktów
public class ProductService extends AbstractProductService {
    // Singleton - jedna instancja serwisu dla całej aplikacji
    private final static ProductService instance = new ProductService(ProductRepository.getInstance());



    private ProductService(ProductRepository repository) {
        super(repository);
    }

    public static ProductService getInstance() {
        return instance;
    }

    @Override
    public void duplicateProduct(String productId, DuplicationModifier modifier) {
        BaseProduct original = repository.getProduct(productId);
        BaseProduct duplicate = cloneOriginalProduct(original);
        applyCustomModifications(duplicate, modifier);
        saveDuplicatedProduct(duplicate);
    }

    private BaseProduct cloneOriginalProduct(BaseProduct original) {
        try {
            return original.clone();
        } catch (CloneNotSupportedException exception) {
            // Week 9, custom exceptions, jakub marciniuk
            throw new ProductDuplicationException("Klonowanie nieudane", exception);
            // End Week 9, custom exceptions, jakub marciniuk
        }
    }

    private void applyCustomModifications(BaseProduct product, DuplicationModifier modifier) {
        if (modifier != null) {
            modifier.modify(product);
        }
    }

    private void saveDuplicatedProduct(BaseProduct duplicate) {
        repository.addProduct(duplicate);
    }

    /*
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
*/
}
// End Week 2, Pattern Singleton 3