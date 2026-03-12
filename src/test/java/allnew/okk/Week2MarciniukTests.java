package allnew.okk;
import allnew.okk.product.Decorator.ExtendedWarrantyDecorator;
import allnew.okk.product.composite.ProductCategoryNode;
import allnew.okk.product.model.*;
import allnew.okk.product.Decorator.*;
import allnew.okk.product.bridge.*;
import allnew.okk.basket.composite.*;
import allnew.okk.basket.composite.*;
import allnew.okk.product.factory.ProductFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Week2MarciniukTests {
    @Test
    void testExtendedWarrantyDecorator() {
        ProductFactory factory = ProductFactory.getInstance();
        PrivateProduct baseProduct = factory.createPrivateProduct()
                .toBuilder()
                .setName("Laptop")
                .setPrice(1000.0)
                .build();


        PurchasableItem decoratedProduct = new ExtendedWarrantyDecorator(baseProduct);

        assertEquals("Laptop + Extended Warranty (12 months)", decoratedProduct.getItemName());
        assertEquals(1100.0, decoratedProduct.getPrice(), 0.01);
    }

    @Test
    void testCountryTaxPolicyPriceOnProduct() {
        CountryTaxPolicy taxPolicyPoland = new CountryTaxPolicy("PL", 0.23);
        CountryTaxPolicy taxPolicyGermany = new CountryTaxPolicy("DE", 0.19);

        PrivateProduct productPoland = new PrivateProduct.Builder()
                .setName("Smartphone")
                .setPrice(500.0)
                .setTaxPolicy(taxPolicyPoland)
                .build();

        PrivateProduct productGermany = new PrivateProduct.Builder()
                .setName("Smartphone")
                .setPrice(500.0)
                .setTaxPolicy(taxPolicyGermany)
                .build();

        assertEquals(115.0, productPoland.getTaxPolicy().calculateTax(productPoland.getPrice()), 0.01);
        assertEquals(95.0, productGermany.getTaxPolicy().calculateTax(productGermany.getPrice()), 0.01);
    }

    @Test
    void testCountryTaxPolicyPriceWithExtendedWarranty() {
        CountryTaxPolicy taxPolicyPoland = new CountryTaxPolicy("PL", 0.23);
        PrivateProduct baseProduct = new PrivateProduct.Builder()
                .setName("Tablet")
                .setPrice(200.0)
                .setTaxPolicy(taxPolicyPoland)
                .build();

        // Dekorujemy produkt przedłużoną gwarancją, która dodaje 100 do ceny bazowej
        ExtendedWarrantyDecorator extendedWarrantyProduct = new ExtendedWarrantyDecorator(baseProduct);

        // Oczekiwana cena to cena bazowa + podatek + koszt przedłużonej gwarancji
        double expectedPrice = extendedWarrantyProduct.getWarrantyCost()
                + taxPolicyPoland.calculateTotalPrice(extendedWarrantyProduct.getPrice() - extendedWarrantyProduct.getWarrantyCost());
        assertEquals(346.0, expectedPrice, 0.01);
    }

    @Test
    void testProductCategoryNode() {
        ProductCategoryNode electronics = new ProductCategoryNode("Electronics");
        ProductCategoryNode computers = new ProductCategoryNode("Computers");
        ProductCategoryNode laptops = new ProductCategoryNode("Laptops");

        electronics.addChild(computers);
        computers.addChild(laptops);

        assertTrue(electronics.getChildren().contains(computers));
        assertTrue(computers.getChildren().contains(laptops));
        assertEquals("Electronics", electronics.getItemName());
        assertEquals("Computers", computers.getItemName());
        assertEquals("Laptops", laptops.getItemName());
    }

    @Test
    void testProductCategoryNodeWithLeafProducts() {
        ProductCategoryNode electronics = new ProductCategoryNode("Electronics");
        ProductCategoryNode computers = new ProductCategoryNode("Computers");
        ProductCategoryNode laptops = new ProductCategoryNode("Laptops");

        PrivateProduct laptop1 = new PrivateProduct.Builder()
                .setName("Laptop 1")
                .setPrice(1000.0)
                .build();

        PrivateProduct laptop2 = new PrivateProduct.Builder()
                .setName("Laptop 2")
                .setPrice(1500.0)
                .build();
        computers.addChild(laptops);
        electronics.addChild(computers);
        laptops.addChild(laptop1);
        laptops.addChild(laptop2);


        assertTrue(computers.getAllLeafProducts().contains(laptop1));
        assertTrue(computers.getAllLeafProducts().contains(laptop2));
        assertTrue(electronics.getAllProducts().contains(laptop1));
        assertTrue(electronics.getAllProducts().contains(laptop2));
    }
}
