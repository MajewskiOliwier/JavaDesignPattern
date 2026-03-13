package allnew.okk.product.factory;

import allnew.okk.product.model.*;

//Week 2, Pattern Singleton 1
// Implementacja fabryki produktów jako Singletona, z metodami tworzącymi domyślne instancje produktów.
public class ProductFactory {
    // Singleton - jedna instancja fabryki dla całej aplikacji
    private static final ProductFactory instance = new ProductFactory();

    // konstruktor tworzący prototypy produktów
    private ProductFactory(){
    }

    // metoda do pobierania instancji fabryki (singleton)
    public static ProductFactory getInstance(){
        return instance;
    }

    // Tydzień 2, Wzorzec Factory Method 1
    // metody do tworzenia produktów, korzystające z builderów, z domyślnymi wartościami
    public PrivateProduct createPrivateProduct(){
        return new PrivateProduct.Builder()
                .setName("Default Private Product")
                .setDescription("Default description")
                .setPrice(0.0)
                .setCategory(ProductCategory.OTHER)
                .setCondition(ProductCondition.NONE)
                .setSellerName("Default Seller")
                .setSellerContact("Default Contact")
                .build();
    }

    // metoda do tworzenia produktów firmowych, korzystająca z buildera, z domyślnymi wartościami
    public CompanyProduct createCompanyProduct(){
        return new CompanyProduct.Builder()
                .setName("Default Company Product")
                .setDescription("Default description")
                .setPrice(0.0)
                .setCategory(ProductCategory.OTHER)
                .setCondition(ProductCondition.NONE)
                .setCompanyName("Default Company")
                .setCompanyContact("Default Contact")
                .build();
    }
    // Koniec, Tydzień 2, Wzorzec Factory Method

}
// End, Week 2, Pattern Singleton 1