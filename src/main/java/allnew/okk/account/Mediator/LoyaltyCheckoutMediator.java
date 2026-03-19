package allnew.okk.account.Mediator;

import allnew.okk.Order.Mediator.CheckoutMediator;
import allnew.okk.Order.Memento.Order;
import allnew.okk.Order.OrderStatus;
import allnew.okk.account.Adapter.AccountDisplayable;

//Week 5, Pattern Mediator 1 Oliwier Majewski
//This is class that implements interface CheckoutMediator which calculates the loyaltyPoints for the accounts that implement LoyaltyEligible interface (PrivateAccount and PrivateAccountAdapter)
//Can be passed in the second Constructor for  OrderFacade class
public class LoyaltyCheckoutMediator implements CheckoutMediator {
    private final LoyaltyService loyaltyService;

    public LoyaltyCheckoutMediator(LoyaltyService loyaltyService){
        this.loyaltyService = loyaltyService;
    }

    @Override
    public void notify(Order order, OrderStatus status) {
        if (status.equals(OrderStatus.PAID)) {
            onPaymentSuccess(order);
        }
    }

    private void onPaymentSuccess(Order order) {
        System.out.println("Mediator: payment succeeded for order, coordinating services...");

        AccountDisplayable customer = order.getCustomer();

        if (customer instanceof LoyaltyEligilble loyaltyEligible) {
            loyaltyService.RewardCustomer(loyaltyEligible, (float) order.getBasket().getPrice());
        } else {
            System.out.println("Mediator: account not eligible for loyalty points, skipping");
        }
    }
}
//End Week 5, Pattern Mediator 1 Oliwier Majewski