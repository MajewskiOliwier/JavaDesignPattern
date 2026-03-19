package allnew.okk.account.Mediator;

import allnew.okk.Order.Mediator.CheckoutMediator;
import allnew.okk.Order.Memento.Order;
import allnew.okk.Order.OrderStatus;

//Week 5, Pattern Mediator 1 Oliwier Majewski
//This is class that implements interface CheckoutMediator which returns nothing
// if the original constructor that doesnt pass instance implementing CheckoutMediator inside the second constructor for  OrderFacade class
public class EmptyCheckoutMediator implements CheckoutMediator {
    public EmptyCheckoutMediator(){}

    @Override
    public void notify(Order order, OrderStatus status) {
        System.out.println("Empty Mediator");
    }
}
//End Week 5, Pattern Mediator 1 Oliwier Majewski
