package allnew.okk;

import allnew.okk.product.model.DigitalProduct;
import allnew.okk.product.repository.ProductRepository;
import allnew.okk.product.service.ProductService;
import allnew.okk.product.state.AvailableState;
import allnew.okk.product.state.OutOfStockState;
import allnew.okk.product.exception.ProductExceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Week13TestsMarciniuk {

    private ProductRepository repository;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repository = ProductRepository.getInstance();
        repository.clear();
        service = ProductService.getInstance();
    }

    // BaseProduct / DigitalProduct
    @Test void testProductCreation() {
        DigitalProduct dp = (DigitalProduct) new DigitalProduct.DigitalBuilder().setName("E-book").setPrice(20.0).build();
        assertEquals("E-book", dp.getName());
    }
    @Test void testProductDefaultState() {
        DigitalProduct dp = (DigitalProduct) new DigitalProduct.DigitalBuilder().build();
        assertTrue(dp.canBePurchased());
    }
    @Test void testProductChangeStateToOutOfStock() {
        DigitalProduct dp = (DigitalProduct) new DigitalProduct.DigitalBuilder().build();
        dp.markOutOfStock();
        assertFalse(dp.canBePurchased());
    }
    @Test void testProductClone() throws Exception {
        DigitalProduct original = (DigitalProduct) new DigitalProduct.DigitalBuilder().setName("Soft").build();
        DigitalProduct clone = (DigitalProduct) original.clone();
        assertEquals(original.getName(), clone.getName());
        assertNotSame(original, clone);
    }
    @Test void testProductObserverAddition() {
        DigitalProduct dp = (DigitalProduct) new DigitalProduct.DigitalBuilder().build();
        dp.addObserver((name, oldP, newP) -> {});
        assertEquals(1, dp.getObservers().size());
    }

    // ProductRepository
    @Test void testRepoAddProduct() {
        DigitalProduct dp = (DigitalProduct) new DigitalProduct.DigitalBuilder().build();
        repository.addProduct(dp);
        assertEquals(1, repository.getAllProducts().size());
    }
    @Test void testRepoGetNonExistentThrowsException() {
        assertThrows(ProductNotFoundException.class, () -> repository.getProduct("FAKE-ID"));
    }
    @Test void testRepoRemoveThrowsExceptionIfMissing() {
        assertThrows(ProductRemovalException.class, () -> repository.removeProduct("MISSING"));
    }
    @Test void testRepoClear() {
        repository.addProduct(new DigitalProduct.DigitalBuilder().build());
        repository.clear();
        assertEquals(0, repository.getAllProducts().size());
    }
    @Test void testRepoFilter() {
        repository.addProduct(new DigitalProduct.DigitalBuilder().setPrice(50.0).build());
        assertEquals(1, repository.filter(p -> p.getPrice() == 50.0).size());
    }

    // ProductService
    @Test void testServiceDuplicateThrowsIfMissing() {
        assertThrows(ProductNotFoundException.class, () -> service.duplicateProduct("MISSING", null));
    }
    @Test void testServiceDuplicateSuccess() {
        DigitalProduct dp = (DigitalProduct) new DigitalProduct.DigitalBuilder().build();
        repository.addProduct(dp);
        String id = repository.getIdByProduct(dp);
        service.duplicateProduct(id, p -> p.setName("Kopia"));
        assertEquals(2, repository.getAllProducts().size());
    }
    @Test void testServiceDuplicateModificationApplied() {
        DigitalProduct dp = (DigitalProduct) new DigitalProduct.DigitalBuilder().setName("Oryginal").build();
        repository.addProduct(dp);
        String id = repository.getIdByProduct(dp);
        service.duplicateProduct(id, p -> p.setName("Zmodyfikowany"));
        assertTrue(repository.getAllProducts().stream().anyMatch(p -> p.getName().equals("Zmodyfikowany")));
    }
    @Test void testSingletonInstance() {
        ProductService s1 = ProductService.getInstance();
        ProductService s2 = ProductService.getInstance();
        assertSame(s1, s2);
    }
    @Test void testNullModifierHandledGracefully() {
        DigitalProduct dp = (DigitalProduct) new DigitalProduct.DigitalBuilder().build();
        repository.addProduct(dp);
        String id = repository.getIdByProduct(dp);
        assertDoesNotThrow(() -> service.duplicateProduct(id, null));
    }

    // State Classes (AvailableState / OutOfStockState)
    @Test void testAvailableStateCanBePurchased() {
        assertTrue(new AvailableState().canBePurchased());
    }
    @Test void testAvailableStateName() {
        assertEquals("Available", new AvailableState().getStateName());
    }
    @Test void testOutOfStockStateCannotBePurchased() {
        assertFalse(new OutOfStockState().canBePurchased());
    }
    @Test void testOutOfStockStateName() {
        assertEquals("Out of Stock", new OutOfStockState().getStateName());
    }
    @Test void testStateInterfacePolymorphism() {
        allnew.okk.product.state.ProductState state = new AvailableState();
        assertTrue(state.canBePurchased());
        state = new OutOfStockState();
        assertFalse(state.canBePurchased());
    }
}