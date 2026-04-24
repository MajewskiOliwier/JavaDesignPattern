package allnew.okk.shop;

import allnew.okk.shop.functional.ShopAction;
import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.model.PhysicalShop;
import allnew.okk.shop.service.ShopAnalyticsService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Week10FunctionalTests {

    @Test
    void testFunctionalInterfacesAndLambdas() {
        ShopAnalyticsService analyticsService = new ShopAnalyticsService();
        List<BaseShop> shops = new ArrayList<>();

        BaseShop openShop = new PhysicalShop.Builder().setName("Open Shop").build();
        BaseShop closedShop = new PhysicalShop.Builder().setName("Closed Shop").build();
        closedShop.closeShop();

        shops.add(openShop);
        shops.add(closedShop);

        // Week 10 - Using Predicate with Lambda expression
        Predicate<BaseShop> isOpen = BaseShop::canAcceptOrders;
        List<BaseShop> activeShops = analyticsService.filterShops(shops, isOpen);
        assertEquals(1, activeShops.size());

        // Week 10 - Using Function with Method Reference
        Function<BaseShop, String> nameExtractor = BaseShop::getName;
        List<String> names = analyticsService.extractShopNames(shops, nameExtractor);
        assertEquals("Open Shop", names.get(0));

        // Week 10 - Custom Functional Interface implemented via LAMBDA
        ShopAction closeAction = (shop) -> {
            System.out.println("Executing Lambda: closing " + shop.getName());
            shop.closeShop();
        };

        analyticsService.executeActionOnShops(shops, closeAction);

        // Verify Lambda impact
        assertEquals("CLOSED", shops.get(0).getShopState().getStateName());
    }

    @Test
    void testAdditionalStreamProcessing() {
        ShopAnalyticsService analyticsService = new ShopAnalyticsService();

        // Test strumienia na stosie
        java.util.Stack<allnew.okk.shop.memento.ShopProfileMemento> historyStack = new java.util.Stack<>();
        historyStack.push(new allnew.okk.shop.memento.ShopProfileMemento("Sklep A", "Opis 1"));
        historyStack.push(new allnew.okk.shop.memento.ShopProfileMemento("Sklep B", "Opis 2"));

        java.util.List<String> names = analyticsService.getHistoryNames(historyStack);
        assertEquals(2, names.size());
        assertTrue(names.contains("Sklep A"));
        assertTrue(names.contains("Sklep B"));

        // Test strumienia na liście Kompozytów (Zliczanie elementów głównych)
        java.util.List<allnew.okk.shop.composite.ShopComponent> components = new java.util.ArrayList<>();
        components.add(new PhysicalShop.Builder().setName("Pojedynczy Sklep").build());

        allnew.okk.shop.composite.ShopNetwork network = new allnew.okk.shop.composite.ShopNetwork("Duża Sieć");
        components.add(network);

        long count = analyticsService.countNetworkComponents(components);
        assertEquals(2, count, "Strumień źle zliczył główne komponenty na liście!");
    }
}