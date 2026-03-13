package allnew.okk.shop.model;

import allnew.okk.shop.composite.ShopComponent;
import allnew.okk.shop.decorator.ShopDisplay;
import allnew.okk.shop.bridge.NotificationSender;

import lombok.Getter;
import lombok.Setter;

// Week 2, Prototype Pattern 1
// Implementation of the Prototype pattern via the Cloneable interface, enabling the creation of shop object copies.
@Getter
@Setter
public abstract class BaseShop implements Cloneable, ShopComponent, ShopDisplay {
    private String name;
    private String description;
    private NotificationSender notificationSender;

    public BaseShop(Builder<?> builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.notificationSender = builder.notificationSender; // CHANGE
    }

    // Week 3, Composite Pattern 2
    // Implementation of ShopComponent interface methods for an individual shop.
    @Override
    public String getDetails() {
        return "Shop: " + name + " (" + description + ")";
    }

    @Override
    public int getShopCount() {
        return 1; // because an individual shop always counts as one
    }
    // End of Week 3, Composite Pattern 2

    // Week 3, Decorator Pattern 2
    // Returns raw shop data.
    // Used by decorators.
    @Override
    public String getDisplayName() {
        return this.name;
    }

    @Override
    public String getDisplayDescription() {
        return this.description;
    }
    // End of Week 3, Decorator Pattern 2


    // Week 3, Bridge Pattern 4
    // Method for sending notifications.
    // Utilizes an interface, allowing it to work for both SMS and emails.
    public void broadcastPromotion(String promoMessage) {
        if (notificationSender != null) {
            notificationSender.sendNotification(this.name, promoMessage);
        } else {
            System.out.println("Shop " + this.name + " does not have a notification system configured.");
        }
    }
    // End of Week 3, Bridge Pattern 4

    // Week 2, Builder Pattern 1
    // Generic builder with generic typing.
    // Thanks to the self() method, derived classes return their own type, enabling fluent method chaining.
    public abstract static class Builder<T extends Builder<T>> {
        private String name = "Default Shop";
        private String description = "Default Description";
        private NotificationSender notificationSender; // CHANGE in Builder

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        public T setNotificationSender(NotificationSender sender) {
            this.notificationSender = sender;
            return self();
        }

        protected abstract T self();
        public abstract BaseShop build();
    }
    // End of Week 2, Builder Pattern 1

    @Override
    public BaseShop clone() throws CloneNotSupportedException {
        try {
            return (BaseShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed");
        }
    }
}
// End of Week 2, Prototype Pattern 1