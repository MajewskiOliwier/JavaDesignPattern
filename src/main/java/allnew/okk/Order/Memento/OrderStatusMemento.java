package allnew.okk.Order.Memento;

import allnew.okk.Order.OrderStatus;

import java.time.LocalDateTime;

// Week 5, Pattern Memento 1 Oliwier Majewski

// Week 7, SRP Oliwier Majewski
// This is Class that will hold Order Status in form of snapshot of a single order status at a point in time for Memento Pattern
public class OrderStatusMemento {
    private final OrderStatus status;
    private final LocalDateTime timestamp;

    public OrderStatusMemento(OrderStatus status) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public OrderStatus getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return status + " at " + timestamp;
    }
}

// End Week 7, SRP Oliwier Majewski
// End Week 5, Pattern Memento 1 Oliwier Majewski