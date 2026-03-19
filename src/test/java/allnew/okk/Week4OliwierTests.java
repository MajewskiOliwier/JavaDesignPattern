package allnew.okk;

import allnew.okk.Order.Facade.OrderFacade;
import allnew.okk.Order.Mediator.CheckoutMediator;
import allnew.okk.Order.Memento.Order;
import allnew.okk.Order.OrderStatus;
import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.account.Adapter.CompanyAccountAdapter;
import allnew.okk.account.Adapter.PrivateAccountAdapter;
import allnew.okk.account.Factory.AccountFactory;
import allnew.okk.account.Mediator.LoyaltyCheckoutMediator;
import allnew.okk.account.Mediator.LoyaltyService;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;
import allnew.okk.basket.Command.AddToBasketCommand;
import allnew.okk.basket.Command.RemoveLastProductFromBasketCommand;
import allnew.okk.basket.Interpreter.*;
import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.basket.composite.ShoppingBasket;
import allnew.okk.payment.Adapter.PayUAdapter;
import allnew.okk.product.Decorator.GiftWrapDecorator;
import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Week4OliwierTests {
    PayUAdapter payUAdapter = new PayUAdapter();

    CompanyAccount companyAccount = AccountFactory.createCompanyAccount()
            .Email("asus@gmail.com")
            .SetLegalName("Asus")
            .Password("zxcvbnm")
            .SetVatNumber("1234567890")
            .Build();

    PrivateAccount privateAccount = AccountFactory.createPrivateAccount()
            .Name("Oliwier")
            .Surname("Majewski")
            .Email("test@gmail.com")
            .build();

    AccountDisplayable companyAccountAdapter = new CompanyAccountAdapter(companyAccount);
    AccountDisplayable privateAccountAdapter = new PrivateAccountAdapter(privateAccount);

    ShoppingBasket buildBasket() {
        CompanyProduct companyProduct1 = new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        CompanyProduct companyProduct2 = new CompanyProduct.Builder()
                .setName("Laptop2")
                .setPrice(51.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        PrivateProduct privateProduct1 = new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .setAccountDisplayable(privateAccountAdapter)
                .build();

        PurchasableItem privateProduct1WithWrap = new GiftWrapDecorator(privateProduct1);

        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem(companyProduct1);
        basket.addItem(companyProduct2);
        basket.addItem(privateProduct1WithWrap);
        return basket;
    }

    Order buildOrder() {
        return new Order(buildBasket(), privateAccountAdapter);
    }

    // ---------------------------------------------------------------
    // Memento Tests
    // ---------------------------------------------------------------

    @Test
    void testOrderStartsAsPending() {
        Order order = buildOrder();
        assertEquals(OrderStatus.PENDING, order.getStatus(),
                "Order should start with PENDING status");
    }

    @Test
    void testOrderStatusIsPaidAfterSuccessfulPlaceOrder() {
        Order order = buildOrder();
        OrderFacade facade = new OrderFacade(payUAdapter);
        facade.placeOrder(order, "PLN");
        assertEquals(OrderStatus.PAID, order.getStatus(),
                "Order status should be PAID after successful placeOrder");
    }

    @Test
    void testUndoRestoresPendingAfterPaid() {
        Order order = buildOrder();
        OrderFacade facade = new OrderFacade(payUAdapter);
        facade.placeOrder(order, "PLN");    // PENDING → PAID
        facade.undoLastStatus(order);       // restores PENDING
        assertEquals(OrderStatus.PENDING, order.getStatus(),"Undo should restore order to PENDING after being PAID");
    }

    @Test
    void testUndoOnEmptyHistoryReturnsFalse() {
        Order order = buildOrder();
        OrderFacade facade = new OrderFacade(payUAdapter);
        boolean result = facade.undoLastStatus(order);
        assertFalse(result, "Undo should return false when there is no history");
    }

    @Test
    void testOrderRemainsUnchangedAfterFailedUndo() {
        Order order = buildOrder();
        OrderFacade facade = new OrderFacade(payUAdapter);
        facade.undoLastStatus(order);
        assertEquals(OrderStatus.PENDING, order.getStatus(),"Order status should remain PENDING when undo has nothing to restore");
    }

    @Test
    void testMementoSavesCorrectTimestamp() {
        Order order = buildOrder();
        var memento = order.saveMemento();
        assertNotNull(memento.getTimestamp(),"Memento should always store a non-null timestamp");
    }

    @Test
    void testMementoPreservesStatus() {
        Order order = buildOrder();
        var memento = order.saveMemento();
        assertEquals(OrderStatus.PENDING, memento.getStatus(),"Memento should preserve the status at the time of saving");
    }

    @Test
    void testHistoryDoesNotExceedFiveSnapshots() {
        OrderFacade facade = new OrderFacade(payUAdapter);

        facade.placeOrder(buildOrder(), "PLN");
        facade.placeOrder(buildOrder(), "PLN");
        facade.placeOrder(buildOrder(), "PLN");

        assertTrue(facade.getStatusHistory().size() <= 5,"History should never exceed 5 snapshots");
    }

    // ---------------------------------------------------------------
    // Commanbd Tests
    // ---------------------------------------------------------------

    CompanyProduct laptop;
    CompanyProduct laptop2;
    PrivateProduct chleb;
    ShoppingBasket basket;
    @BeforeEach
    void setUp() {
        basket = new ShoppingBasket();

        laptop = new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        laptop2 = new CompanyProduct.Builder()
                .setName("Laptop2")
                .setPrice(51.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();

        chleb = new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .setAccountDisplayable(privateAccountAdapter)
                .build();
    }
    CompanyProduct buildLaptop() {
        return new CompanyProduct.Builder()
                .setName("Laptop1")
                .setPrice(199.00)
                .setCompanyName("Asus")
                .setNIP("1234567890")
                .setAccountDisplayable(companyAccountAdapter)
                .build();
    }

    PrivateProduct buildChleb() {
        return new PrivateProduct.Builder()
                .setName("Chleb")
                .setPrice(50.00)
                .setSellerName("Oliwier")
                .setAccountDisplayable(privateAccountAdapter)
                .build();
    }

    @Test
    void testAddCommandAddsItemToBasket() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop()));
        assertEquals(1, basket.getChildren().size(),
                "Basket should have 1 item after AddToBasketCommand");
    }

    @Test
    void testAddCommandIncreasesBasketSize() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop()));
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));
        assertEquals(2, basket.getChildren().size(),
                "Basket should have 2 items after two AddToBasketCommands");
    }

    @Test
    void testRemoveCommandRemovesItemFromBasket() {
        ShoppingBasket basket = new ShoppingBasket();
        CompanyProduct laptop = buildLaptop();
        basket.executeCommand(new AddToBasketCommand(basket, laptop));
        basket.executeCommand(new RemoveLastProductFromBasketCommand(basket, laptop));
        assertFalse(basket.getChildren().contains(laptop),"Basket should not contain laptop after RemoveFromBasketCommand");
    }

    @Test
    void testUndoAddRemovesItemFromBasket() {
        ShoppingBasket basket = new ShoppingBasket();
        CompanyProduct laptop = buildLaptop();
        basket.executeCommand(new AddToBasketCommand(basket, laptop));
        basket.undo();
        assertFalse(basket.getChildren().contains(laptop), "Undo of AddToBasketCommand should remove laptop from basket");
    }

    @Test
    void testUndoRemoveRestoresItemToBasket() {
        ShoppingBasket basket = new ShoppingBasket();
        CompanyProduct laptop = buildLaptop();
        basket.executeCommand(new AddToBasketCommand(basket, laptop));
        basket.executeCommand(new RemoveLastProductFromBasketCommand(basket, laptop));
        basket.undo();
        assertTrue(basket.getChildren().contains(laptop), "Undo of RemoveFromBasketCommand should restore laptop to basket");
    }

    @Test
    void testMultipleUndosRestoreBasketToEmpty() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop()));
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));
        basket.undo();
        basket.undo();
        assertTrue(basket.getChildren().isEmpty(), "Basket should be empty after undoing all add commands");
    }

    @Test
    void testUndoOnEmptyBasketHistoryReturnsFalse() {
        ShoppingBasket basket = new ShoppingBasket();
        assertFalse(basket.undo(), "Undo should return false when basket history is empty");
    }

    @Test
    void testBasketPriceIsCorrectAfterUndo() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop())); // 199.00
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));  // 50.00
        basket.undo();                                                         // remove chleb
        assertEquals(199.00, basket.getPrice(), 0.01, "Basket price should reflect only remaining items after undo");
    }

    @Test
    void testHistorySizeMatchesExecutedCommands() {
        ShoppingBasket basket = new ShoppingBasket();
        CompanyProduct laptop = buildLaptop();
        basket.executeCommand(new AddToBasketCommand(basket, laptop));
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));
        basket.executeCommand(new RemoveLastProductFromBasketCommand(basket, laptop));
        assertEquals(3, basket.historySize(), "History size should match the number of executed commands");
    }

    // ---------------------------------------------------------------
    // Interpreter Tests
    // ---------------------------------------------------------------


    @Test
    void testPriceExpressionGTFiltersCorrectly() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop())); // 199.00
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));  // 50.00

        List<PurchasableItem> result = basket.filter(new PriceExpression(100, ComparisonOperator.GT));

        assertEquals(1, result.size(), "Only laptop should pass GT 100 filter");
    }

    @Test
    void testPriceExpressionLTFiltersCorrectly() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop())); // 199.00
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));  // 50.00

        List<PurchasableItem> result = basket.filter(new PriceExpression(100, ComparisonOperator.LT));

        assertEquals(1, result.size(), "Only chleb should pass LT 100 filter");
    }

    @Test
    void testSellerExpressionFiltersCorrectly() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop()));
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));

        List<PurchasableItem> result = basket.filter(new SellerNameContainExpression("Asus"));

        assertEquals(1, result.size(), "Only Asus product should pass seller filter");
    }

    @Test
    void testItemNameContainsExpressionFiltersCorrectly() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop()));
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));

        List<PurchasableItem> result = basket.filter(new ItemNameContainExpression("Lap"));

        assertEquals(1, result.size(), "Only Laptop should pass name contains 'Lap' filter");
    }

    @Test
    void testAndExpressionCombinesTwoFilters() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop())); // Asus, 199.00
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));  // Oliwier, 50.00

        BasketInterpreterExpression query = new AndExpression(
                new SellerNameContainExpression("Asus"),
                new PriceExpression(100, ComparisonOperator.GT)
        );

        List<PurchasableItem> result = basket.filter(query);

        assertEquals(1, result.size(), "Only Asus laptop over 100 PLN should pass AND filter");
    }

    @Test
    void testFilterReturnsEmptyWhenNothingMatches() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.executeCommand(new AddToBasketCommand(basket, buildLaptop()));
        basket.executeCommand(new AddToBasketCommand(basket, buildChleb()));

        List<PurchasableItem> result = basket.filter(new SellerNameContainExpression("Nike"));

        assertTrue(result.isEmpty(), "Filter should return empty list when no items match");
    }

    @Test
    void testFilterOnEmptyBasketReturnsEmptyList() {
        ShoppingBasket basket = new ShoppingBasket();

        List<PurchasableItem> result = basket.filter(new PriceExpression(100, ComparisonOperator.GT));

        assertTrue(result.isEmpty(), "Filter on empty basket should return empty list");
    }

    // ---------------------------------------------------------------
    // Mediator Tests
    // ---------------------------------------------------------------

    PrivateAccountAdapter privateAccountAdapter2 = new PrivateAccountAdapter(privateAccount);
    LoyaltyCheckoutMediator loyaltyCheckoutMediator = new LoyaltyCheckoutMediator(new LoyaltyService());

    @Test
    void testLoyaltyPointsAddedForPrivateAccountAfterOrder() {
        Order order = new Order(buildBasket(), privateAccountAdapter);
        OrderFacade facade = new OrderFacade(payUAdapter, loyaltyCheckoutMediator);

        facade.placeOrder(order, "PLN");

        PrivateAccountAdapter adapter = privateAccountAdapter2;
        assertTrue(adapter.getLoyaltyPoints() > 0,"Private account should have loyalty points after successful order");
    }

    @Test
    void testLoyaltyPointsEqualFloorOfOrderTotal() {
        Order order = new Order(buildBasket(), privateAccountAdapter); // Order has total value of 305PLN and with the rate of 0.5 pts for 1 PLN
        OrderFacade facade = new OrderFacade(payUAdapter, loyaltyCheckoutMediator);

        facade.placeOrder(order, "PLN");

        PrivateAccountAdapter adapter = (PrivateAccountAdapter) privateAccountAdapter;
        assertEquals(152, adapter.getLoyaltyPoints(),"Loyalty points should be floor of (305.00 PLN * 0.5) = 152 points");
    }

    @Test
    void testCompanyAccountDoesNotReceiveLoyaltyPoints() {
        Order order = new Order(buildBasket(), companyAccountAdapter);
        OrderFacade facade = new OrderFacade(payUAdapter, loyaltyCheckoutMediator);

        assertDoesNotThrow(() -> facade.placeOrder(order, "PLN"),"Company account should be silently skipped by mediator without throwing");
    }

    @Test
    void testNoOpMediatorDoesNotAffectLoyaltyPoints() {
        Order order = new Order(buildBasket(), privateAccountAdapter);
        OrderFacade facade = new OrderFacade(payUAdapter); // default constructor does not uses the NoOpCheckoutMediator

        facade.placeOrder(order, "PLN");

        PrivateAccountAdapter adapter = (PrivateAccountAdapter) privateAccountAdapter;
        assertEquals(0, adapter.getLoyaltyPoints(),"NoOp mediator should not add any loyalty points");
    }
}
