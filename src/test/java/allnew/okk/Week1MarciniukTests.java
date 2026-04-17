package allnew.okk;
import allnew.okk.product.factory.ProductFactory;
import allnew.okk.product.model.PrivateProduct;
import allnew.okk.product.model.ProductCategory;
import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.ProductCondition;
import allnew.okk.product.repository.ProductRepository;
import allnew.okk.product.service.NameSuffixModifier;
import allnew.okk.product.service.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class Week1MarciniukTests {
    @Test
    void testFactoryPrivateProductCreation() {
        ProductFactory factory = ProductFactory.getInstance();
        PrivateProduct product = factory.createPrivateProduct();

        assertNotNull(product);
        assertEquals("Default Private Product", product.getName());
        assertEquals("Default description", product.getDescription());
        assertEquals(0.0, product.getPrice());
        assertEquals(ProductCategory.OTHER, product.getCategory());
        assertEquals("Default Seller", product.getSellerName());
        assertEquals("Default Contact", product.getSellerContact());
    }

    @Test
    void testFactorySingleton() {
        ProductFactory factory1 = ProductFactory.getInstance();
        ProductFactory factory2 = ProductFactory.getInstance();

        assertSame(factory1, factory2, "ProductFactory powinien być singletonem");
    }
    @Test
    void testFactoryCompanyProductCreation() {
        ProductFactory factory = ProductFactory.getInstance();
        CompanyProduct product = factory.createCompanyProduct();

        assertNotNull(product);
        assertEquals("Default Company Product", product.getName());
        assertEquals("Default description", product.getDescription());
        assertEquals(0.0, product.getPrice());
        assertEquals(ProductCategory.OTHER, product.getCategory());
        assertEquals("Default Company", product.getCompanyName());
        assertEquals("Default Contact", product.getCompanyContact());
    }
    @Test
    void testFactoryPrivateProductCreationWithCustomValues() {
        ProductFactory factory = ProductFactory.getInstance();
        PrivateProduct product = factory.createPrivateProduct()
                .toBuilder()
                .setName("jakas nazwa")
                .setDescription("jakis opis")
                .setPrice(99.99)
                .setCategory(ProductCategory.ELECTRONICS)
                .setSellerName("jakis sprzedawca")
                .setSellerContact("jakis kontakt")
                .build();

        assertNotNull(product);
        assertEquals("jakas nazwa", product.getName());
        assertEquals("jakis opis", product.getDescription());
        assertEquals(99.99, product.getPrice());
        assertEquals(ProductCategory.ELECTRONICS, product.getCategory());
        assertEquals("jakis sprzedawca", product.getSellerName());
        assertEquals("jakis kontakt", product.getSellerContact());
    }
    @Test
    void testFactoryCompanyProductCreationWithCustomValues() {
        ProductFactory factory = ProductFactory.getInstance();
        CompanyProduct product = factory.createCompanyProduct()
                .toBuilder()
                .setName("jakas nazwa")
                .setDescription("jakis opis")
                .setPrice(199.99)
                .setCategory(ProductCategory.FASHION)
                .setCompanyName("jakas firma")
                .setCompanyContact("jakis kontakt")
                .build();

        assertNotNull(product);
        assertEquals("jakas nazwa", product.getName());
        assertEquals("jakis opis", product.getDescription());
        assertEquals(199.99, product.getPrice());
        assertEquals(ProductCategory.FASHION, product.getCategory());
        assertEquals("jakas firma", product.getCompanyName());
        assertEquals("jakis kontakt", product.getCompanyContact());
    }
    @Test
    void testFactoryPrivateProductCloning() {
        ProductFactory factory = ProductFactory.getInstance();
        PrivateProduct original = factory.createPrivateProduct()
                .toBuilder()
                .setName("Inna nazwa niz default")
                .build();

        PrivateProduct clone = original.clone();

        assertNotNull(clone);
        assertEquals(original.getName(), clone.getName());
        assertEquals(original.getDescription(), clone.getDescription());
        assertEquals(original.getPrice(), clone.getPrice());
        assertEquals(original.getCategory(), clone.getCategory());
        assertEquals(original.getSellerName(), clone.getSellerName());
        assertEquals(original.getSellerContact(), clone.getSellerContact());
    }
    @Test
    void testProductServiceDuplicationToRepository(){
        ProductRepository repository = ProductRepository.getInstance();
        ProductService service = ProductService.getInstance();

        PrivateProduct product = new PrivateProduct.Builder()
                .setName("Test")
                .setDescription("Test opis")
                .setPrice(10.0)
                .setCategory(ProductCategory.AUTOMOTIVE)
                .setCondition(ProductCondition.NEW)
                .setSellerName("Test sprzedawca")
                .setSellerContact("Test kontakt")
                .build();

        repository.addProduct(product);

        try{
            service.duplicateProduct(repository.getIdByProduct(product), new NameSuffixModifier("(Copy)"));
        }catch (CloneNotSupportedException e){
            fail("Klonowanie powinno być obsługiwane, ale wystąpił błąd: " + e.getMessage());
        }

        var productsInRepo = repository.getAllProducts();
        assertEquals(2, productsInRepo.size(), "Powinny być 2 produkty w repozytorium po duplikacji");
        assertEquals("Test", productsInRepo.get(1).getName());
        assertEquals("Test (Copy)", productsInRepo.get(0).getName());
        repository.clear();
    }

    @Test
    void testRepositoryRemoval() {
        ProductRepository repository = ProductRepository.getInstance();

        PrivateProduct product = new PrivateProduct.Builder()
                .setName("Do usuniecia")
                .setDescription("Do usuniecia")
                .setPrice(5.0)
                .setCategory(ProductCategory.FASHION)
                .setCondition(ProductCondition.USED)
                .setSellerName("Do usuniecia")
                .setSellerContact("Do usuniecia")
                .build();

        repository.addProduct(product);
        var productId = repository.getIdByProduct(product);

        assertTrue(repository.removeProduct(productId), "Produkt powinien zostać usunięty");
        assertNull(repository.getProduct(productId), "Usunięty produkt nie powinien być dostępny w repozytorium");
        repository.clear();
    }

    @Test
    void testRepositoryGetAllProducts() {
        ProductRepository repository = ProductRepository.getInstance();
        repository.getAllProducts().clear(); // Clear repository before test

        PrivateProduct product1 = new PrivateProduct.Builder()
                .setName("Produkt 1")
                .setDescription("First product")
                .setPrice(15.0)
                .setCategory(ProductCategory.FASHION)
                .setCondition(ProductCondition.NEW)
                .setSellerName("Sprzedawca 1")
                .setSellerContact("Kontact 1")
                .build();

        PrivateProduct product2 = new PrivateProduct.Builder()
                .setName("Produkt 2")
                .setDescription("Drugi produkt")
                .setPrice(25.0)
                .setCategory(ProductCategory.AUTOMOTIVE)
                .setCondition(ProductCondition.USED)
                .setSellerName("Sprzedawca 2")
                .setSellerContact("Kontact 2")
                .build();

        repository.addProduct(product1);
        repository.addProduct(product2);

        var allProducts = repository.getAllProducts();
        assertEquals(2, allProducts.size(), "Powinny być 2 produkty w repozytorium");
        assertTrue(allProducts.contains(product1), "Repozytorium powinno zawierać Product 1");
        assertTrue(allProducts.contains(product2), "Repozytorium powinno zawierać Product 2");
        repository.clear();
    }

    @Test
    void testRepositoryClear() {
        ProductRepository repository = ProductRepository.getInstance();

        PrivateProduct product = new PrivateProduct.Builder()
                .setName("Do wyczyszczenia")
                .setDescription("Do wyczyszczenia")
                .setPrice(20.0)
                .setCategory(ProductCategory.AUTOMOTIVE)
                .setCondition(ProductCondition.NEW)
                .setSellerName("Do wyczyszczenia")
                .setSellerContact("Do wyczyszczenia")
                .build();

        repository.addProduct(product);
        repository.clear();

        var allProducts = repository.getAllProducts();
        assertTrue(allProducts.isEmpty(), "Repozytorium powinno być puste po wywołaniu clear()");
        // i tak musze usuwać za każdym razem bo to singleton i nie nadaje sie do testów
        repository.clear();
    }

    @Test
    void testRepositoryUpdateProduct() {
        ProductRepository repository = ProductRepository.getInstance();

        PrivateProduct product = new PrivateProduct.Builder()
                .setName("Do zmiany")
                .setDescription("Do zmiany")
                .setPrice(30.0)
                .setCategory(ProductCategory.ELECTRONICS)
                .setCondition(ProductCondition.USED)
                .setSellerName("Do zmiany")
                .setSellerContact("Do zmiany")
                .build();

        repository.addProduct(product);
        var productId = repository.getIdByProduct(product);

        PrivateProduct updatedProduct = new PrivateProduct.Builder()
                .setName("Zmieniona nazwa")
                .setDescription("Zmieniony opis")
                .setPrice(35.0)
                .setCategory(ProductCategory.ELECTRONICS)
                .setCondition(ProductCondition.NEW)
                .setSellerName("Zmieniony sprzedawca")
                .setSellerContact("Zmieniony kontakt")
                .build();

        repository.updateProduct(productId, updatedProduct);

        var retrievedProduct = repository.getProduct(productId);
        assertNotNull(retrievedProduct, "Zaktualizowany produkt powinien być dostępny w repozytorium");
        assertEquals("Zmieniona nazwa", retrievedProduct.getName());
        assertEquals("Zmieniony opis", retrievedProduct.getDescription());
        assertEquals(35.0, retrievedProduct.getPrice());
        assertEquals(ProductCategory.ELECTRONICS, retrievedProduct.getCategory());
        assertEquals(ProductCondition.NEW, retrievedProduct.getCondition());
        repository.clear();
    }

}
