package allnew.okk.shop.memento;

import java.util.Stack;

// Week 9 - Maintaining Clean Code Principles

// Week 5, Pattern Memento 2
// Caretaker class responsible for keeping the history of shop profile changes.
// It manages the mementos but never modifies their internal state.
public class ShopProfileHistory {

    private static final String LOG_SAVED = "[HISTORIA] Zapisano migawkę profilu sklepu.";
    private static final String LOG_RESTORING = "[HISTORIA] Przywracanie poprzedniego profilu sklepu...";

    private final Stack<ShopProfileMemento> history = new Stack<>();

    public void save(ShopProfileMemento memento) {
        if (memento == null) {
            throw new IllegalArgumentException("Nie można zapisać pustej migawki w historii.");
        }
        history.push(memento);
        System.out.println(LOG_SAVED);
    }

    public ShopProfileMemento undo() {
        if (history.isEmpty()) {
            throw new IllegalStateException("Brak zapisanych stanów profilu do przywrócenia.");
        }
        System.out.println(LOG_RESTORING);
        return history.pop();
    }

    public int getHistorySize() {
        return history.size();
    }
}
// End Week 5, Pattern Memento 2