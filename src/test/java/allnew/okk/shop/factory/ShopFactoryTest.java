package allnew.okk.shop.factory;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShopFactoryTest {

    @Test
    public void testShopFactoryIsSingleton() {
        ShopFactory instance1 = ShopFactory.getInstance();
        ShopFactory instance2 = ShopFactory.getInstance();

        assertSame(instance1, instance2, "Fabryka powinna być Singletonem (ten sam obiekt w pamięci)");
    }

    @Test
    public void testCreateOnlineShop() {
        ShopFactory factory = ShopFactory.getInstance();

        OnlineShop onlineShop = factory.createOnlineShop("E-Księgarnia", "Najlepsze książki w sieci", "www.ksiazki.pl");

        assertNotNull(onlineShop, "Sklep nie powinien być nullem");
        assertEquals("E-Księgarnia", onlineShop.getName());
        assertEquals("Najlepsze książki w sieci", onlineShop.getDescription());
        assertEquals("www.ksiazki.pl", onlineShop.getWebsiteUrl());
    }

    @Test
    public void testCreatePhysicalShop() {
        ShopFactory factory = ShopFactory.getInstance();

        PhysicalShop physicalShop = factory.createPhysicalShop("Warzywniak", "Świeże owoce i warzywa", "ul. Polna 5, 00-001 Warszawa");

        assertNotNull(physicalShop, "Sklep nie powinien być nullem");
        assertEquals("Warzywniak", physicalShop.getName());
        assertEquals("Świeże owoce i warzywa", physicalShop.getDescription());
        assertEquals("ul. Polna 5, 00-001 Warszawa", physicalShop.getAddress());
    }
}