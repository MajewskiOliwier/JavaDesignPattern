package allnew.okk.shop.memento;

import java.time.LocalDateTime;

// Week 9 - Maintaining Clean Code Principles

// Week 5, Pattern Memento 1
// This class stores the snapshot of the shop's profile (name and description) at a given point in time.
// It acts as the "Memento" in the design pattern.
public class ShopProfileMemento {

    private static final String TO_STRING_FORMAT = "Migawka Profilu [%s]: %s";

    private final String name;
    private final String description;
    private final LocalDateTime timestamp;

    public ShopProfileMemento(String name, String description) {
        if (name == null || description == null) {
            throw new IllegalArgumentException("Nazwa i opis w migawce nie mogą być puste.");
        }
        this.name = name;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, timestamp, name);
    }
}
// End Week 5, Pattern Memento 1