package allnew.okk.shop;

import allnew.okk.shop.composite.ShopNetwork;
import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import allnew.okk.shop.observer.ShopObserver;
import allnew.okk.shop.state.ClosedState;
import allnew.okk.shop.state.OpenState;
import allnew.okk.shop.state.SuspendedState;
import allnew.okk.shop.strategy.DistanceBasedShippingStrategy;
import allnew.okk.shop.strategy.FlatRateShippingStrategy;
import allnew.okk.shop.strategy.FreeOverThresholdShippingStrategy;
import allnew.okk.shop.template.CsvShopReport;
import allnew.okk.shop.template.HtmlShopReport;
import allnew.okk.shop.template.ShopReportTemplate;
import allnew.okk.shop.visitor.MaintenanceCostVisitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Week6ShopTests {

    // 1. TEST WZORCA STRATEGY (Strategia)
    @Test
    void testShippingCostStrategies() {
        BaseShop shop = new PhysicalShop.Builder().setName("Sklep Strategiczny").build();

        // Test 1: Płaska stawka (zawsze 15 PLN niezależnie od odległości i ceny)
        shop.setShippingStrategy(new FlatRateShippingStrategy(15.0));
        assertEquals(15.0, shop.calculateDelivery(100.0, 50.0));

        // Test 2: Stawka za kilometr (np. 2 PLN za kilometr)
        shop.setShippingStrategy(new DistanceBasedShippingStrategy(2.0));
        assertEquals(100.0, shop.calculateDelivery(10.0, 50.0)); // 50 km * 2 PLN

        // Test 3: Darmowa dostawa powyżej 200 PLN, inaczej 20 PLN
        shop.setShippingStrategy(new FreeOverThresholdShippingStrategy(200.0, 20.0));
        assertEquals(20.0, shop.calculateDelivery(150.0, 10.0)); // Poniżej progu
        assertEquals(0.0, shop.calculateDelivery(250.0, 10.0));  // Powyżej progu
    }

    // 2. TEST WZORCA OBSERVER (Obserwator)
    @Test
    void testShopObserverNotifications() {
        BaseShop shop = new OnlineShop.Builder().setName("Sklep Obserwowany").build();

        // Tworzymy klasę anonimową (szpiega) zliczającą otrzymane powiadomienia na potrzeby testu
        var spyObserver = new ShopObserver() {
            int count = 0;
            String lastMessage = "";

            @Override
            public void update(String shopName, String promoMessage) {
                count++;
                lastMessage = promoMessage;
            }
        };

        shop.addObserver(spyObserver);

        // Wywołujemy promocję
        shop.broadcastPromotion("Wielka wyprzedaż!");

        // Sprawdzamy, czy szpieg otrzymał powiadomienie z poprawnym tekstem
        assertEquals(1, spyObserver.count, "Obserwator powinien otrzymać dokładnie jedno powiadomienie!");
        assertEquals("Wielka wyprzedaż!", spyObserver.lastMessage);
    }

    // 3. TEST WZORCA STATE (Stan)
    @Test
    void testShopStateTransitions() {
        BaseShop shop = new PhysicalShop.Builder().setName("Sklep Zmiennostanowy").build();

        // Sklep domyślnie powinien być OTWARTY
        assertTrue(shop.getShopState() instanceof OpenState, "Sklep nie rodzi się otwarty!");
        assertTrue(shop.canAcceptOrders());

        // Zmiana na ZAMKNIĘTY
        shop.closeShop();
        assertTrue(shop.getShopState() instanceof ClosedState);
        assertFalse(shop.canAcceptOrders(), "Zamknięty sklep nie powinien przyjmować zamówień!");

        // Zmiana na ZAWIESZONY
        shop.suspendShop();
        assertTrue(shop.getShopState() instanceof SuspendedState);
        assertFalse(shop.canAcceptOrders(), "Zawieszony sklep nie powinien przyjmować zamówień!");
    }

    // 4. TEST WZORCA TEMPLATE METHOD (Szablon)
    @Test
    void testShopReportTemplates() {
        BaseShop shop = new OnlineShop.Builder().setName("Sklep Raportowy").build();

        ShopReportTemplate htmlReport = new HtmlShopReport();
        ShopReportTemplate csvReport = new CsvShopReport();

        // Metoda generateReport() używa System.out.println, więc aby test przeszedł pozytywnie,
        // upewniamy się, że cała sekwencja wykonuje się bez rzucania wyjątków (np. NullPointerException).
        assertDoesNotThrow(() -> {
            htmlReport.generateReport(shop);
            csvReport.generateReport(shop);
        });
    }

    // 5. TEST WZORCA VISITOR (Odwiedzający)
    @Test
    void testMaintenanceCostVisitorOnNetwork() {
        ShopNetwork network = new ShopNetwork("Sieć Ogólnopolska");

        // Koszt: 5000 (baza) + 1500 (odbiór paczek) = 6500 PLN
        network.addShopComponent(new PhysicalShop.Builder()
                .setName("Stacjonarny Z Paczkami")
                .setDropOffAvailable(true)
                .build());

        // Koszt: 5000 PLN (baza, brak paczek)
        network.addShopComponent(new PhysicalShop.Builder()
                .setName("Stacjonarny Zwykły")
                .setDropOffAvailable(false)
                .build());

        // Koszt: 800 PLN (serwery)
        network.addShopComponent(new OnlineShop.Builder()
                .setName("Internetowy")
                .build());

        MaintenanceCostVisitor visitor = new MaintenanceCostVisitor();

        // Odwiedzamy całą strukturę jednym wezwaniem
        network.accept(visitor);

        // Oczekiwana suma: 6500 + 5000 + 800 = 12300 PLN
        assertEquals(12300.0, visitor.getTotalCost(), "Wizytator źle policzył koszty całej sieci!");
    }
}