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

// Week 2, Pattern Prototype 1
// Implementacja wzorca Prototype poprzez interfejs Cloneable, umożliwiająca tworzenie kopii obiektów sklepów.
@Getter
@Setter
public abstract class BaseShop implements Cloneable, ShopComponent, ShopDisplay {
    private String name;
    private String description;
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
        this.shippingStrategy = builder.shippingStrategy; // Wstrzyknięcie z Buildera
    }

    // Week 2, Pattern Composite 2
    // Implementacja metod z interfejsu ShopComponent dla pojedynczego sklepu.
    @Override
    public String getDetails() {
        return "Sklep: " + name + " (" + description + ")";
    }

    @Override
    public int getShopCount() {
        return 1; // bo pojedynczy sklep zawsze jest jeden
    }
    // End Week 3, Pattern Composite 2

    // Week 3, Pattern Decorator 2
    // Zwraca czyste dane sklepu.
    // Wykorzystywane przy dekoratorach.
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
    // Metoda wysyłająca powiadomienie.
    // Wykorzystuje interfejs, dzięki temu działa zarówno dla SMSów i emaili.
    public void broadcastPromotion(String promoMessage) {
        // 1. Wysyłanie systemowe (Bridge)
        if (notificationSender != null) {
            notificationSender.sendNotification(this.name, promoMessage);
        } else {
            System.out.println("Sklep " + this.name + " nie ma skonfigurowanego systemu powiadomień.");
        }

        // 2. Powiadamianie wszystkich subskrybentów sklepu (Observer)
        for (ShopObserver observer : observers) {
            observer.update(this.name, promoMessage);
        }
    }
    // End Week 3, Pattern Bridge 4


    // Week 5, Pattern Memento 3
    // Creates a snapshot of the current shop profile (Originator role).
    public ShopProfileMemento saveProfileMemento() {
        return new ShopProfileMemento(this.name, this.description);
    }

    // Restores the shop profile from a provided snapshot.
    public void restoreProfileMemento(ShopProfileMemento memento) {
        if (memento != null) {
            this.name = memento.getName();
            this.description = memento.getDescription();
            System.out.println("Shop profile restored to: " + this.name);
        }
    }
    // End Week 5, Pattern Memento 3

    public double calculateDelivery(double orderTotal, double distanceInKm) {
        if (shippingStrategy != null) {
            return shippingStrategy.calculateShippingCost(orderTotal, distanceInKm);
        }
        return 0.0; // Domyślnie brak kosztów
    }

    // Week 6, Pattern Observer 3
    // Methods to manage subscribers (Attach & Detach)
    public void addObserver(ShopObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("New subscriber added to shop: " + this.name);
        }
    }

    public void removeObserver(ShopObserver observer) {
        observers.remove(observer);
        System.out.println("Subscriber removed from shop: " + this.name);
    }
    // End Week 6, Pattern Observer 3

    // Week 6, Pattern State 5
    // State management methods allowing the shop to transition between different operational states
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

    // Metoda delegująca odpowiedzialność do konkretnego stanu
    public boolean canAcceptOrders() {
        return shopState.canAcceptOrders();
    }
    // End Week 6, Pattern State 5

    // Week 6, Pattern Visitor 5
    public abstract void accept(allnew.okk.shop.visitor.ShopVisitor visitor);

    // Week 2, Pattern Builder 1
    // Generyczny builder z generycznym typowaniem.
    // Dzięki metodzie self() klasy pochodne zwracają własny typ, co umożliwia płynne wywoływanie metod.
    public abstract static class Builder<T extends Builder<T>> {
        private String name = "Default Shop";
        private String description = "Default Description";
        private NotificationSender notificationSender; // ZMIANA w Builderze
        private ShopCategory category = ShopCategory.ELECTRONICS;
        private ShippingCostStrategy shippingStrategy = new FlatRateShippingStrategy(15.0);

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
    public BaseShop clone() throws CloneNotSupportedException {
        try {
            return (BaseShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Klonowanie nie powiodło się");
        }
    }
}
// End, Week 2, Pattern Prototype 1