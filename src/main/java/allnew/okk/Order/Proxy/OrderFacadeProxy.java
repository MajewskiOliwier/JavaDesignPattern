package allnew.okk.Order.Proxy;

import allnew.okk.Order.Facade.OrderFacade;
import allnew.okk.Order.Memento.Order;
import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.account.Prototype.BaseAccount;
import allnew.okk.account.Singleton.CurrentSession;
import allnew.okk.basket.composite.ShoppingBasket;

// Week 4, Pattern Proxy 3 Oliwier Majewski
// This Protection Proxy class that will guard OrderFacade before placeOrder() is executed
public class OrderFacadeProxy {
    private final OrderFacade orderFacade;

    public OrderFacadeProxy(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public boolean placeOrder(Order order, AccountDisplayable customer, String currency) {

        // Week 6, Pattern State 1 Oliwier Majewski
        BaseAccount loggedAccount = CurrentSession.getInstance().isPrivateAccount()
                ? CurrentSession.getInstance().getAsPrivate()
                : CurrentSession.getInstance().getAsCompany();

        if (!loggedAccount.canPlaceOrder()) {
            System.out.println("Proxy: blocked — account state does not allow orders");
            return false;
        }
        // End Week 6, Pattern State 1 Oliwier Majewski

        // Check 1 — valid payment method
        if (!customer.hasValidPaymentMethod()) {
            System.out.println("Order blocked: " + customer.GetDisplayName() + " has no valid payment method.");
            return false;
        }

        // All checks passed — delegate to the real facade
        return orderFacade.placeOrder(order, currency);
    }
}
// End Week 4, Pattern Proxy 3 Oliwier Majewski
