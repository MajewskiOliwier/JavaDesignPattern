package allnew.okk;

import allnew.okk.product.factory.ProductFactory;
import allnew.okk.product.flyweight.*;
import allnew.okk.product.facade.*;
import allnew.okk.product.model.ProductCategory;
import allnew.okk.product.model.ProductCondition;
import allnew.okk.product.proxy.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Week3MarciniukTests {
    @Test
    void testProductConditionFlyweight() {
        ProductConditionFlyweight newCondition = ProductConditionRegistry.get(ProductCondition.NEW);
        ProductConditionFlyweight usedCondition = ProductConditionRegistry.get(ProductCondition.USED);

        assertEquals("Brand new product, never used.", newCondition.getDescription());
        assertEquals("Used product, may have signs of wear.", usedCondition.getDescription());

        assertTrue(newCondition == ProductConditionRegistry.get(ProductCondition.NEW));
        assertTrue(usedCondition == ProductConditionRegistry.get(ProductCondition.USED));
    }

    @Test
    void testProductSearchFacade() {
        ProductSearchFacade facade = new ProductSearchFacade();
        ProductRepositoryProxy proxy = new ProductRepositoryProxy();
        ProductFactory factory = ProductFactory.getInstance();
        proxy.addProduct(factory.createPrivateProduct().toBuilder()
                .setName("Private Car")
                .setCategory(ProductCategory.AUTOMOTIVE)
                .setCondition(ProductCondition.NEW)
                .setPrice(500).build());
        proxy.addProduct(factory.createCompanyProduct().toBuilder()
                .setName("Company Laptop")
                .setCategory(ProductCategory.ELECTRONICS)
                .setCondition(ProductCondition.NEW)
                .setPrice(1000).build());
        proxy.addProduct(factory.createCompanyProduct().toBuilder()
                .setName("Used Phone")
                .setCategory(ProductCategory.ELECTRONICS)
                .setCondition(ProductCondition.USED)
                .setPrice(300).build());

        assertEquals(1, facade.searchProductsByName("Car").size());
        assertEquals(2, facade.searchProductsByCategory(ProductCategory.ELECTRONICS).size());
        assertEquals(1, facade.searchProductsByCondition(ProductCondition.USED).size());
        assertEquals(2, facade.searchProductsByPriceRange(0, 600).size());
        assertEquals(1, facade.searchProductsCheaperThan(400).size());

        proxy.clearProducts();
    }

    @Test
    void testProductRepositoryProxy() {
        ProductRepositoryProxy proxy = new ProductRepositoryProxy();
        ProductFactory factory = ProductFactory.getInstance();
        proxy.addProduct(factory.createPrivateProduct().toBuilder()
                .setName("Private Bike")
                .setCategory(ProductCategory.AUTOMOTIVE)
                .setCondition(ProductCondition.NEW)
                .setPrice(200).build());

        assertEquals(1, proxy.getAllProducts().size());
        assertEquals("Private Bike", proxy.getAllProducts().getFirst().getName());

        proxy.removeProduct(proxy.getIdByProduct(proxy.getAllProducts().getFirst()));
        assertEquals(0, proxy.getAllProducts().size());

        proxy.clearProducts();
    }

}
