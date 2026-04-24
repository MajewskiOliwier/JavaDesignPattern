package allnew.okk.shop.model;

import allnew.okk.shop.composite.ShopComponent;
import allnew.okk.shop.decorator.ShopDisplay;
import allnew.okk.shop.bridge.NotificationSender;
import allnew.okk.shop.flyweight.ShopCategory;
import allnew.okk.shop.memento.ShopProfileMemento;
import allnew.okk.shop.strategy.ShippingCostStrategy;
import allnew.okk.shop.strategy.FlatRateShippingStrategy;
import allnew.okk.shop.observer.ShopObserver;
import allnew.okk.shop.state.ShopState;
import allnew.okk.shop.state.OpenState;
import allnew.okk.shop.state.ClosedState;
import allnew.okk.shop.state.SuspendedState;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


// Week 9 - Maintaining Clean Code Principles
// Week 8 - Liskov Substitution Principle 1 (Base Class)
// The BaseShop base class defines a consistent interface and behavior for all store types.

// Week 2, Pattern Prototype 1
// Implementation of the Prototype pattern via the Cloneable interface, enabling the creation of copies of store objects.
@Getter
@Setter
public abstract class BaseShop implements Cloneable, ShopComponent, ShopDisplay {

    private static final int SINGLE_SHOP_COUNT = 1;
    private static final double DEFAULT_FLAT_RATE = 15.0;

    private String name;
    private String description;
    // Week 8 - Dependency Inversion Principle 5 (High-Level Module)
// Dependency Injection
// BaseShop depends on an abstraction, not a specific class (e.g., EmailNotificationSender).
    private NotificationSender notificationSender;
    private ShopCategory category;
    private ShippingCostStrategy shippingStrategy;
    private final List<ShopObserver> observers = new ArrayList<>();
    private ShopState shopState = new OpenState();

    public BaseShop(Builder<?> builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.notificationSender = builder.notificationSender;
        this.category = builder.category;
        this.shippingStrategy = builder.shippingStrategy;
    }

    // Week 3, Pattern Composite 2
    @Override
    public String getDetails() {
        return "Sklep: " + name + " (" + description + ")";
    }

    @Override
    public int getShopCount() {
        return SINGLE_SHOP_COUNT;
    }
    // End Week 3, Pattern Composite 2

    // Week 3, Pattern Decorator 2
    @Override
    public String getDisplayName() {
        return this.name;
    }

    @Override
    public String getDisplayDescription() {
        return this.description;
    }
    // End Week 3, Pattern Decorator 2

    // Week 3, Pattern Bridge 4
    public void broadcastPromotion(String promoMessage) {
        sendSystemNotification(promoMessage);
        notifySubscribers(promoMessage);
    }

    private void sendSystemNotification(String promoMessage) {
        if (notificationSender != null) {
            notificationSender.sendNotification(this.name, promoMessage);
        } else {
            System.out.println("Sklep " + this.name + " nie ma skonfigurowanego systemu powiadomień.");
        }
    }

    private void notifySubscribers(String promoMessage) {
        for (ShopObserver observer : observers) {
            observer.update(this.name, promoMessage);
        }
    }
    // End Week 3, Pattern Bridge 4

    // Week 5, Pattern Memento 3
    public ShopProfileMemento saveProfileMemento() {
        return new ShopProfileMemento(this.name, this.description);
    }

    public void restoreProfileMemento(ShopProfileMemento memento) {
        if (memento == null) {
            throw new IllegalArgumentException("Nie można przywrócić profilu z pustego memento.");
        }
        this.name = memento.getName();
        this.description = memento.getDescription();
        System.out.println("Shop profile restored to: " + this.name);
    }
    // End Week 5, Pattern Memento 3

    public double calculateShippingCost(double orderTotal, double distanceInKm) {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Brak strategii wysyłki dla sklepu: " + this.name);
        }
        return shippingStrategy.calculateShippingCost(orderTotal, distanceInKm);
    }

    // Week 6, Pattern Observer 3
    public void addObserver(ShopObserver observer) {
        if (observers.contains(observer)) {
            throw new IllegalArgumentException("Ten subskrybent jest już przypisany do sklepu.");
        }
        observers.add(observer);
        System.out.println("New subscriber added to shop: " + this.name);
    }

    public void removeObserver(ShopObserver observer) {
        observers.remove(observer);
        System.out.println("Subscriber removed from shop: " + this.name);
    }
    // End Week 6, Pattern Observer 3

    // Week 6, Pattern State 5
    public void openShop() {
        this.shopState = new OpenState();
        System.out.println("Shop " + this.name + " is now OPEN.");
    }

    public void closeShop() {
        this.shopState = new ClosedState();
        System.out.println("Shop " + this.name + " is now CLOSED.");
    }

    public void suspendShop() {
        this.shopState = new SuspendedState();
        System.out.println("Shop " + this.name + " is now SUSPENDED.");
    }

    public boolean canAcceptOrders() {
        return shopState.canAcceptOrders();
    }
    // End Week 6, Pattern State 5

    // Week 6, Pattern Visitor 5
    public abstract void accept(allnew.okk.shop.visitor.ShopVisitor visitor);

    // Week 2, Pattern Builder 1
    public abstract static class Builder<T extends Builder<T>> {
        private String name = "Default Shop";
        private String description = "Default Description";
        private NotificationSender notificationSender;
        private ShopCategory category = ShopCategory.ELECTRONICS;
        private ShippingCostStrategy shippingStrategy = new FlatRateShippingStrategy(DEFAULT_FLAT_RATE);

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

        public T setCategory(ShopCategory category) {
            this.category = category;
            return self();
        }

        public T setShippingStrategy(ShippingCostStrategy shippingStrategy) {
            this.shippingStrategy = shippingStrategy;
            return self();
        }

        protected abstract T self();
        public abstract BaseShop build();
    }
    // End Week 2, Pattern Builder 1

    @Override
    public BaseShop clone() {
        try {
            return (BaseShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Klonowanie nie powiodło się", e);
        }
    }
}