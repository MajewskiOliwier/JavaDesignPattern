package allnew.okk.product.factory;

import allnew.okk.product.model.*;

import java.util.HashMap;
import java.util.Map;


public class ProductFactory {
    private static final ProductFactory instance = new ProductFactory();

    // konstruktor tworzący prototypy produktów
    private ProductFactory(){
    }

    // metoda do pobierania instancji fabryki (singleton)
    public static ProductFactory getInstance(){
        return instance;
    }


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


}
