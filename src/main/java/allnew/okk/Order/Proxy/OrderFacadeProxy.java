package allnew.okk.Order.Proxy;

import allnew.okk.Order.Facade.OrderFacade;
import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.composite.ShoppingBasket;

// Week 4, Pattern Proxy 3 Oliwier Majewski
// This Protection Proxy class that will guard OrderFacade before placeOrder() is executed
public class OrderFacadeProxy {
    private final OrderFacade orderFacade;

    public OrderFacadeProxy(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public boolean placeOrder(ShoppingBasket basket, AccountDisplayable customer, String currency) {

        // Check 1 — valid payment method
        if (!customer.hasValidPaymentMethod()) {
            System.out.println("Order blocked: " + customer.GetDisplayName() + " has no valid payment method.");
            return false;
        }

        // All checks passed — delegate to the real facade
        return orderFacade.placeOrder(basket, currency);
    }
}
// End Week 4, Pattern Proxy 3 Oliwier Majewski
