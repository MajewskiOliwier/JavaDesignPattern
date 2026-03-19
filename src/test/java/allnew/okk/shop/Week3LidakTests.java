package allnew.okk.shop;

import allnew.okk.shop.adapter.PhysicalShopShippingAdapter;
import allnew.okk.shop.bridge.EmailNotificationSender;
import allnew.okk.shop.bridge.SmsNotificationSender;
import allnew.okk.shop.composite.ShopNetwork;
import allnew.okk.shop.decorator.PolishBadgeDecorator;
import allnew.okk.shop.decorator.PremiumBadgeDecorator;
import allnew.okk.shop.decorator.ShopDisplay;
import allnew.okk.shop.decorator.VerifiedBadgeDecorator;
import allnew.okk.shop.facade.ShopManagementFacade;
import allnew.okk.shop.flyweight.ShopCategory;
import allnew.okk.shop.flyweight.ShopCategoryFlyweight;
import allnew.okk.shop.flyweight.ShopCategoryRegistry;
import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import allnew.okk.shop.proxy.ShopRepositoryProxy;
import allnew.okk.shop.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Week3LidakTests {

    @BeforeEach
    void setUp() {
        ShopRepository.getInstance().clear();
    }

    @Test
    void testPhysicalShopShippingAdapter() {
        PhysicalShop physicalShop = new PhysicalShop.Builder()
                .setName("Sklep Testowy")
                .setAddress("ul. Testowa 1")
                .setDropOffAvailable(true)
                .build();

        PhysicalShopShippingAdapter adapter = new PhysicalShopShippingAdapter(physicalShop);

        assertEquals("PUNKT ODBIORU: Sklep Testowy", adapter.getPointName());
        assertEquals("ul. Testowa 1", adapter.getFullAddress());
        assertTrue(adapter.isAvailableForDropOff());
    }

    @Test
    void testShopNetworkComposite() {
        ShopNetwork nationalNetwork = new ShopNetwork("Sieć Krajowa");
        ShopNetwork localNetwork = new ShopNetwork("Sieć Lokalna");

        OnlineShop onlineShop = new OnlineShop.Builder().setName("Sklep Internetowy").build();
        PhysicalShop physicalShop = new PhysicalShop.Builder().setName("Sklep Stacjonarny").build();

        localNetwork.addShopComponent(physicalShop);
        nationalNetwork.addShopComponent(localNetwork);
        nationalNetwork.addShopComponent(onlineShop);

        assertEquals(2, nationalNetwork.getShopCount());
        assertTrue(nationalNetwork.getDetails().contains("Sieć Krajowa"));
        assertTrue(nationalNetwork.getDetails().contains("Sieć Lokalna"));
    }

    @Test
    void testNotificationBridge() {
        BaseShop emailShop = new OnlineShop.Builder()
                .setName("Email Shop")
                .setNotificationSender(new EmailNotificationSender())
                .build();

        BaseShop smsShop = new PhysicalShop.Builder()
                .setName("SMS Shop")
                .setNotificationSender(new SmsNotificationSender())
                .build();

        assertDoesNotThrow(() -> {
            emailShop.broadcastPromotion("Test maila");
            smsShop.broadcastPromotion("Test SMSa");
        });
    }

    @Test
    void testShopDecorators() {
        ShopDisplay shop = new PhysicalShop.Builder()
                .setName("Mój Sklep")
                .setDescription("Zwykły opis")
                .build();

        shop = new VerifiedBadgeDecorator(shop);
        shop = new PolishBadgeDecorator(shop);
        shop = new PremiumBadgeDecorator(shop);

        String finalName = shop.getDisplayName();
        String finalDescription = shop.getDisplayDescription();

        assertTrue(finalName.contains("⭐"));
        assertTrue(finalName.contains("🇵🇱"));
        assertTrue(finalName.contains("✔️"));
        assertTrue(finalName.contains("Mój Sklep"));

        assertTrue(finalDescription.contains("Zwykły opis"));
        assertTrue(finalDescription.contains("PREMIUM"));
    }

    @Test
    void testShopManagementFacade() {
        ShopManagementFacade facade = new ShopManagementFacade();

        facade.registerStandardOnlineShop("Online Test", "Desc", "url");
        ShopDisplay premiumShop = facade.registerPremiumPhysicalShop("Premium Test", "Desc", "Addr");

        assertTrue(premiumShop.getDisplayName().contains("⭐"));

        assertEquals(2, ShopRepository.getInstance().getAllShops().size());
    }

    @Test
    void testShopRepositoryProxyCaching() {
        ShopRepositoryProxy proxy = new ShopRepositoryProxy();
        proxy.clear();

        BaseShop shop = new OnlineShop.Builder().setName("Cache Shop").build();
        proxy.addShop(shop);

        List<BaseShop> firstFetch = proxy.getAllShops();
        assertEquals(1, firstFetch.size());

        List<BaseShop> secondFetch = proxy.getAllShops();
        assertSame(firstFetch, secondFetch, "Proxy nie buforuje poprawnie - powinno zwrócić tę samą instancję z pamięci");

        proxy.addShop(new OnlineShop.Builder().setName("New Shop").build());
        List<BaseShop> thirdFetch = proxy.getAllShops();
        assertNotSame(firstFetch, thirdFetch, "Proxy nie zresetowało Cache po dodaniu nowego obiektu");
        assertEquals(2, thirdFetch.size());
    }

    @Test
    void testShopCategoryFlyweight() {
        ShopCategoryFlyweight firstCategory = ShopCategoryRegistry.get(ShopCategory.ELECTRONICS);
        ShopCategoryFlyweight secondCategory = ShopCategoryRegistry.get(ShopCategory.ELECTRONICS);

        assertSame(firstCategory, secondCategory, "Rejestr Flyweight tworzy duplikaty w pamięci!");

        assertEquals("electronics.png", firstCategory.getCategoryIconUrl());
    }
}