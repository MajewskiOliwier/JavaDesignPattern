package allnew.okk.shop.model;

import allnew.okk.shop.composite.ShopComponent;
import allnew.okk.shop.decorator.ShopDisplay;
import allnew.okk.shop.bridge.NotificationSender;
import allnew.okk.shop.flyweight.ShopCategory;
import allnew.okk.shop.memento.ShopProfileMemento;

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

    public BaseShop(Builder<?> builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.notificationSender = builder.notificationSender;
        this.category = builder.category;
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
        if (notificationSender != null) {
            notificationSender.sendNotification(this.name, promoMessage);
        } else {
            System.out.println("Sklep " + this.name + " nie ma skonfigurowanego systemu powiadomień.");
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



    // Week 2, Pattern Builder 1
    // Generyczny builder z generycznym typowaniem.
    // Dzięki metodzie self() klasy pochodne zwracają własny typ, co umożliwia płynne wywoływanie metod.
    public abstract static class Builder<T extends Builder<T>> {
        private String name = "Default Shop";
        private String description = "Default Description";
        private NotificationSender notificationSender; // ZMIANA w Builderze
        private ShopCategory category = ShopCategory.ELECTRONICS;

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