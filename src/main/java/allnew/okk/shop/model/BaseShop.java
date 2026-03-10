package allnew.okk.shop.model;

import lombok.Getter;
import lombok.Setter;

// Bazowa klasa abstrakcyjna implementująca wzorzec Prototype (Cloneable)
@Getter
@Setter
public abstract class BaseShop implements Cloneable {
    private String name;
    private String description;

    // Konstruktor przyjmujący Buildera
    public BaseShop(Builder<?> builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    // Bazowa, generyczna klasa Builder dla sklepów, z domyślnymi wartościami
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

    // Wzorzec Prototype - implementacja metody clone()
    @Override
    public BaseShop clone() throws CloneNotSupportedException {
        try {
            return (BaseShop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Klonowanie nie powiodło się");
        }
    }
}