package allnew.okk.Order.Facade;

import allnew.okk.Currency.Flyweight.CurrencyFlyweight;
import allnew.okk.Currency.Flyweight.CurrencyRegistry;
import allnew.okk.Currency.Flyweight.CurrencyType;
import allnew.okk.Order.Memento.Order;
import allnew.okk.Order.Memento.OrderStatusHistory;
import allnew.okk.Order.OrderStatus;
import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.basket.composite.SellerBasket;
import allnew.okk.basket.composite.ShoppingBasket;
import allnew.okk.payment.Adapter.PaymentGateway;
import allnew.okk.product.model.BaseProduct;

//Week 4, Pattern Facade 1 Oliwier Majewski
//Week 4, Pattern Facade 1 Oliwier Majewski
//Facade class that is responsible for streamlining placing of order
public class OrderFacade {
    private final PaymentGateway paymentGateway;
    private final OrderStatusHistory statusHistory = new OrderStatusHistory();

    public OrderFacade(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }


    //this method is responsible for :
    // * organising items by seller name
    // * looping and processing payment for all of them
    public boolean placeOrder(Order order, String currency) {

        //Week 4, Pattern Flyweight 1 Oliwier Majewski
        CurrencyType currencyType = CurrencyType.fromString(currency);
        if (currencyType == null) {
            System.out.println("Unsupported currency: " + currency);
            return false;
        }

        CurrencyFlyweight currencyFlyweight = CurrencyRegistry.get(currencyType);
        //Week 4, Pattern Flyweight 1 Oliwier Majewski

        //Week 5, Pattern Memento 1 Oliwier Majewski
        statusHistory.save(order.saveMemento());
        //End Week 5, Pattern Memento 1 Oliwier Majewski

        order.getBasket().OrganizeBySeller(); //Divides items by the seller

        //Week 5, Pattern Iterator 1 Oliwier Majewski
        for (PurchasableItem item : order.getBasket()) {
        //End Week 5, Pattern Iterator 1 Oliwier Majewski
            float amount = (float) item.getPrice();
            var sellerAccount =  item.getSellerAccount();

            boolean success = paymentGateway.processPayment(amount, sellerAccount, currency);

            if (!success) {
                System.out.println("Payment failed for seller: " + item.getSellerName());
                return false;
            }
        }

        //Week 5, Pattern Memento 1 Oliwier Majewski
        order.setStatus(OrderStatus.PAID);
        statusHistory.save(order.saveMemento());
        //End Week 5, Pattern Memento 1 Oliwier Majewski

        return true;
    }

    //Week 5, Pattern Memento 1 Oliwier Majewski
    public boolean undoLastStatus(Order order) {
        var memento = statusHistory.undo();
        if (memento == null)
            return false;

        order.restoreMemento(memento);
        return true;
    }

    public OrderStatusHistory getStatusHistory() {
        return statusHistory;
    }
    //End Week 5, Pattern Memento 1 Oliwier Majewski
}
//End Week 4, Pattern Facade 1 Oliwier Majewski