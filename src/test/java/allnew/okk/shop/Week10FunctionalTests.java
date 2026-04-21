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
}