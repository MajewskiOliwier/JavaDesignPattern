package allnew.okk.Order.Memento;

import allnew.okk.Order.OrderStatus;
import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.composite.ShoppingBasket;

// Week 5, Pattern Memento 1 Oliwier Majewski

// Week 7, SRP Oliwier Majewski
// This class is part of memento design pattern, and it manages creation and  the mementos
public class Order {
    private final ShoppingBasket basket;
    private final AccountDisplayable customer;
    private OrderStatus status;

    public Order(ShoppingBasket basket, AccountDisplayable customer) {
        this.basket = basket;
        this.customer = customer;
        this.status = OrderStatus.PENDING;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() { return status; }
    public ShoppingBasket getBasket() { return basket; }
    public AccountDisplayable getCustomer() { return customer; }

    // Creates a snapshot of current status
    public OrderStatusMemento saveMemento() {
        return new OrderStatusMemento(status);
    }

    // Restores status from a snapshot
    public void restoreMemento(OrderStatusMemento memento) {
        this.status = memento.getStatus();
    }
}

// End Week 7, SRP Oliwier Majewski
// End Week 5, Pattern Memento 1 Oliwier Majewski