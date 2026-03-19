package allnew.okk.Order.Mediator;

import allnew.okk.Order.Memento.Order;
import allnew.okk.Order.OrderStatus;

public interface CheckoutMediator {
    public void notify(Order order, OrderStatus status);
}
