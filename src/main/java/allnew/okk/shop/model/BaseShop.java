package allnew.okk.shop.model;

import lombok.Getter;
import lombok.Setter;

// Tydzień 2, Wzorzec Prototype 1
// Implementacja wzorca Prototype poprzez interfejs Cloneable, umożliwiająca tworzenie kopii obiektów sklepów.
@Getter
@Setter
public abstract class BaseShop implements Cloneable {
    private String name;
    private String description;

    public BaseShop(Builder<?> builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

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