package allnew.okk.shop.model;

import allnew.okk.shop.composite.ShopComponent;
import lombok.Getter;
import lombok.Setter;

// Tydzień 2, Wzorzec Prototype 1
// Implementacja wzorca Prototype poprzez interfejs Cloneable, umożliwiająca tworzenie kopii obiektów sklepów.
@Getter
@Setter
public abstract class BaseShop implements Cloneable, ShopComponent {
    private String name;
    private String description;

    public BaseShop(Builder<?> builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    // Tydzień 3, Wzorzec Composite 2
    // Implementacja metod z interfejsu ShopComponent dla pojedynczego sklepu.
    @Override
    public String getDetails() {
        return "Sklep: " + name + " (" + description + ")";
    }

    @Override
    public int getShopCount() {
        return 1; // bo pojedynczy sklep zawsze jest jeden
    }
    // Koniec, Tydzień 3, Wzorzec Composite 2

    // Tydzień 2, Wzorzec Builder 1
    // Generyczny builder z generycznym typowaniem.
    // Dzięki metodzie self() klasy pochodne zwracają własny typ, co umożliwia płynne wywoływanie metod.
    public abstract static class Builder<T extends Builder<T>> {
        private String name = "Default Shop";
        private String description = "Default Description";

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        protected abstract T self();
        public abstract BaseShop build();
    }
    // Koniec, Tydzień 2, Wzorzec Builder 1

    @Override
    public BaseShop clone() throws CloneNotSupportedException {
        try {
            return (BaseShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Klonowanie nie powiodło się");
        }
    }
}
// Koniec, Tydzień 2, Wzorzec Prototype 1