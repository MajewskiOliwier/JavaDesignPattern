package allnew.okk.shop.memento;

import java.time.LocalDateTime;

// Week 5, Pattern Memento 1
// This class stores the snapshot of the shop's profile (name and description) at a given point in time.
// It acts as the "Memento" in the design pattern.
public class ShopProfileMemento {
    private final String name;
    private final String description;
    private final LocalDateTime timestamp;

    public ShopProfileMemento(String name, String description) {
        this.name = name;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Profile Snapshot [" + timestamp + "]: " + name;
    }
}
// End Week 5, Pattern Memento 1