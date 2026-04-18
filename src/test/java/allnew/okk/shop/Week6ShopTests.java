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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Week6ShopTests {

    // 1. TEST STRATEGY PATTERN
    @Test
    void testShippingCostStrategies() {
        BaseShop shop = new PhysicalShop.Builder().setName("Sklep Strategiczny").build();

        shop.setShippingStrategy(new FlatRateShippingStrategy(15.0));
        assertEquals(15.0, shop.calculateDelivery(100.0, 50.0));

        shop.setShippingStrategy(new DistanceBasedShippingStrategy(2.0));
        assertEquals(100.0, shop.calculateDelivery(10.0, 50.0)); // 50 km * 2 PLN

        shop.setShippingStrategy(new FreeOverThresholdShippingStrategy(200.0, 20.0));
        assertEquals(20.0, shop.calculateDelivery(150.0, 10.0)); // Below the threshold
        assertEquals(0.0, shop.calculateDelivery(250.0, 10.0));  // Above the threshold
    }

    // 2. TEST OBSERVER PATTERN
    @Test
    void testShopObserverNotifications() {
        BaseShop shop = new OnlineShop.Builder().setName("Sklep Obserwowany").build();

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

        shop.broadcastPromotion("Wielka wyprzedaż!");

        assertEquals(1, spyObserver.count, "The observer should receive exactly one notification!");
        assertEquals("Wielka wyprzedaż!", spyObserver.lastMessage);
    }

    // 3. TEST STATE PATTERN
    @Test
    void testShopStateTransitions() {
        BaseShop shop = new PhysicalShop.Builder().setName("Sklep Zmiennostanowy").build();

        // The shop should be OPEN by default
        assertTrue(shop.getShopState() instanceof OpenState, "The shop is not initially open!");
        assertTrue(shop.canAcceptOrders());

        // Change to CLOSED
        shop.closeShop();
        assertTrue(shop.getShopState() instanceof ClosedState);
        assertFalse(shop.canAcceptOrders(), "A closed shop should not accept orders!");

        // Change to SUSPENDED
        shop.suspendShop();
        assertTrue(shop.getShopState() instanceof SuspendedState);
        assertFalse(shop.canAcceptOrders(), "A suspended shop should not accept orders!");
    }

    // 4. TEST TEMPLATE METHOD PATTERN
    @Test
    void testShopReportTemplates() {
        BaseShop shop = new OnlineShop.Builder().setName("Sklep Raportowy").build();

        ShopReportTemplate htmlReport = new HtmlShopReport();
        ShopReportTemplate csvReport = new CsvShopReport();

        assertDoesNotThrow(() -> {
            htmlReport.generateReport(shop);
            csvReport.generateReport(shop);
        });
    }

    // 5. TEST VISITOR PATTERN
    @Test
    void testMaintenanceCostVisitorOnNetwork() {
        ShopNetwork network = new ShopNetwork("Sieć Ogólnopolska");

        // Cost: 5000 (base) + 1500 (drop-off) = 6500 PLN
        network.addShopComponent(new PhysicalShop.Builder()
                .setName("Stacjonarny Z Paczkami")
                .setDropOffAvailable(true)
                .build());

        // Cost: 5000 PLN (base, no drop-off)
        network.addShopComponent(new PhysicalShop.Builder()
                .setName("Stacjonarny Zwykły")
                .setDropOffAvailable(false)
                .build());

        // Cost: 800 PLN (servers)
        network.addShopComponent(new OnlineShop.Builder()
                .setName("Internetowy")
                .build());

        MaintenanceCostVisitor visitor = new MaintenanceCostVisitor(Map.of("physical_base", 5000.0, "physical_dropoff", 1500.0, "online_base", 800.0));

        // Visit the entire structure with a single call
        network.accept(visitor);

        // Expected total: 6500 + 5000 + 800 = 12300 PLN
        assertEquals(12300.0, visitor.getTotalCost(), "The visitor incorrectly calculated the costs of the entire network!");
    }
}