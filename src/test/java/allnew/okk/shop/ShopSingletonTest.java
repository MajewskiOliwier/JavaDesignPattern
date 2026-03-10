package allnew.okk.shop;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.repository.ShopRepository;
import allnew.okk.shop.service.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopSingletonTest {

    // Używamy metody clear(), aby każdy test zaczynał się z czystą bazą testów
    // Ta metoda uruchamia się automatycznie przed każdym z testów
    @BeforeEach
    public void setUp() {
        ShopRepository.getInstance().clear();
    }

    // Sprawdzenie czy 2 instancje repozytorium wskazują na to samo miejsce w pamięci
    @Test
    public void testShopRepositoryIsSingleton() {
        // Pobieramy instancję dwa razy
        ShopRepository instance1 = ShopRepository.getInstance();
        ShopRepository instance2 = ShopRepository.getInstance();

        assertSame(instance1, instance2, "Obie instancje ShopRepository powinny być tym samym obiektem w pamięci!");
    }

    // Sprawdzenie czy 2 instancje serwisu wskazują na to samo miejsce w pamięci
    @Test
    public void testShopServiceIsSingleton() {
        ShopService instance1 = ShopService.getInstance();
        ShopService instance2 = ShopService.getInstance();

        assertSame(instance1, instance2, "Obie instancje ShopService powinny być tym samym obiektem w pamięci!");
    }

    @Test
    public void testDuplicateShopInService() throws CloneNotSupportedException {
        ShopRepository repo = ShopRepository.getInstance();
        ShopService service = ShopService.getInstance();

        OnlineShop originalShop = new OnlineShop.Builder()
                .setName("Oryginalny Sklep")
                .setDescription("Opis testowy")
                .setWebsiteUrl("www.test.pl")
                .build();

        repo.addShop(originalShop);

        String firstShopId = "SHOP-1";

        service.duplicateShop(firstShopId);

        assertEquals(2, repo.getAllShops().size(), "Repozytorium powinno zawierać 2 sklepy po duplikacji");

        boolean cloneExists = repo.getAllShops().stream()
                .anyMatch(shop -> shop.getName().equals("Oryginalny Sklep (Filia)"));

        assertTrue(cloneExists, "W repozytorium powinien znajdować się sklonowany sklep ze zmienioną nazwą");
    }
}