package allnew.okk.shop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Week2ShopBuilderTests {

    @Test
    public void testOnlineShopBuilder() {
        OnlineShop onlineShop = new OnlineShop.Builder()
                .setName("Super Buty Online")
                .setDescription("Najlepszy sklep w sieci")
                .setWebsiteUrl("www.superbuty.pl")
                .build();

        assertEquals("Super Buty Online", onlineShop.getName());
        assertEquals("Najlepszy sklep w sieci", onlineShop.getDescription());
        assertEquals("www.superbuty.pl", onlineShop.getWebsiteUrl());
    }

    @Test
    public void testPhysicalShopBuilder() {
        PhysicalShop physicalShop = new PhysicalShop.Builder()
                .setName("Super Buty Stacjonarnie")
                .setDescription("Sklep w centrum miasta")
                .setAddress("ul. Główna 1, 00-001 Warszawa")
                .build();

        assertEquals("Super Buty Stacjonarnie", physicalShop.getName());
        assertEquals("Sklep w centrum miasta", physicalShop.getDescription());
        assertEquals("ul. Główna 1, 00-001 Warszawa", physicalShop.getAddress());
    }

    @Test
    public void testPrototypeCloneMethod() {
        OnlineShop originalShop = new OnlineShop.Builder()
                .setName("Oryginalny Sklep")
                .setDescription("Oryginalny opis")
                .setWebsiteUrl("www.oryginal.pl")
                .build();

        OnlineShop clonedShop = originalShop.clone();

        assertNotSame(originalShop, clonedShop, "Sklonowany obiekt powinien być nową instancją w pamięci");

        assertEquals(originalShop.getName(), clonedShop.getName());
        assertEquals(originalShop.getDescription(), clonedShop.getDescription());
        assertEquals(originalShop.getWebsiteUrl(), clonedShop.getWebsiteUrl());
    }
}