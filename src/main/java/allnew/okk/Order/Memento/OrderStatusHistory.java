package allnew.okk.Order.Memento;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

// Week 4, Pattern Memento 1 Oliwier Majewski
// this class manages the history stack and enforces the snapshot limit

// Week 7, SRP 1 Oliwier Majewski
public class OrderStatusHistory {
    private static final int MAX_SNAPSHOTS = 2;
    private final Deque<OrderStatusMemento> history = new ArrayDeque<>();

    public void save(OrderStatusMemento memento) {
        if (history.size() == MAX_SNAPSHOTS) {
            history.pollLast(); // drop oldest when limit reached
        }
        history.push(memento); // newest always on top
    }

    public OrderStatusMemento undo() {
        if (history.size() < 2) {
            System.out.println("No previous status to restore");
            throw new IllegalStateException();
        }

        history.pop();
        return history.peek();
    }

    public List<OrderStatusMemento> getHistory() {
        return new ArrayList<>(history);
    }

    public int size() { return history.size(); }
}

// End Week 7, SRP 1 Oliwier Majewski
// End Week 5, Pattern Memento 1 Oliwier Majewski