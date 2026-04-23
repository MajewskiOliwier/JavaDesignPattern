package allnew.okk.product.factory;

import allnew.okk.product.model.*;

//Week 2, Pattern Singleton 1, jakub marciniuk
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

    // Week 2, Wzorzec Factory Method 1, jakub marciniuk
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
                .setID(0)
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
                .setID(0)
                .build();
    }
    // End week 2, Wzorzec Factory Method, jakub marciniuk

}
// End, Week 2, Pattern Singleton 1, jakub marciniuk