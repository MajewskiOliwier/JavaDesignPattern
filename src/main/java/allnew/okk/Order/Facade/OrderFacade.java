package allnew.okk.Order.Facade;

import allnew.okk.Currency.Flyweight.CurrencyFlyweight;
import allnew.okk.Currency.Flyweight.CurrencyRegistry;
import allnew.okk.Currency.Flyweight.CurrencyType;
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

    public OrderFacade(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }


    //this method is responsible for :
    // * organising items by seller name
    // * looping and processing payment for all of them
    public boolean placeOrder(ShoppingBasket basket, String currency) {

        //Week 4, Pattern Flyweight 1 Oliwier Majewski
        CurrencyType currencyType = CurrencyType.fromString(currency);
        if (currencyType == null) {
            System.out.println("Unsupported currency: " + currency);
            return false;
        }

        CurrencyFlyweight currencyFlyweight = CurrencyRegistry.get(currencyType);
        //Week 4, Pattern Flyweight 1 Oliwier Majewski

        basket.OrganizeBySeller(); //Divides items by the seller

        for (PurchasableItem item : basket.getChildren()) {
            float amount = (float) item.getPrice();
            var sellerAccount =  item.getSellerAccount();

            boolean success = paymentGateway.processPayment(amount, sellerAccount, currency);

            if (!success) {
                System.out.println("Payment failed for seller: " + item.getSellerName());
                return false;
            }
        }

        return true;
    }
}
//End Week 4, Pattern Facade 1 Oliwier Majewski