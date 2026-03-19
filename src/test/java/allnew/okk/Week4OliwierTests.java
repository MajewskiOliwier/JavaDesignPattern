package allnew.okk;

import allnew.okk.Order.Facade.OrderFacade;
import allnew.okk.Order.Memento.Order;
import allnew.okk.Order.OrderStatus;
import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.account.Adapter.CompanyAccountAdapter;
import allnew.okk.account.Adapter.PrivateAccountAdapter;
import allnew.okk.account.Factory.AccountFactory;
import allnew.okk.account.Prototype.CompanyAccount;
import allnew.okk.account.Prototype.PrivateAccount;
import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.basket.composite.ShoppingBasket;
import allnew.okk.payment.Adapter.PayUAdapter;
import allnew.okk.product.Decorator.GiftWrapDecorator;
import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;
import org.junit.jupiter.api.Test;

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
}
