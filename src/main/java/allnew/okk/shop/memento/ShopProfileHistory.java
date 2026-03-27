package allnew.okk.shop.memento;

import java.util.Stack;

// Week 5, Pattern Memento 2
// Caretaker class responsible for keeping the history of shop profile changes.
// It manages the mementos but never modifies their internal state.
public class ShopProfileHistory {
    private final Stack<ShopProfileMemento> history = new Stack<>();

    // Saves a new snapshot to the history stack
    public void save(ShopProfileMemento memento) {
        history.push(memento);
        System.out.println("[HISTORY] Saved shop profile snapshot.");
    }

    // Retrieves and removes the most recent snapshot for the undo operation
    public ShopProfileMemento undo() {
        if (history.isEmpty()) {
            System.out.println("[HISTORY] No previous profile state to restore.");
            return null;
        }
        System.out.println("[HISTORY] Restoring previous shop profile...");
        return history.pop();
    }

    public int getHistorySize() {
        return history.size();
    }
}
// End Week 5, Pattern Memento 2