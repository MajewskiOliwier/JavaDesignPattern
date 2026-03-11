package allnew.okk.product.model;

import allnew.okk.basket.composite.PurchasableItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Bazowa klasa produktu, zawierająca wspólne właściwości i metody dla różnych typów produktów
@Getter
@Setter
public abstract class BaseProduct  implements Cloneable, PurchasableItem {
    private String name;
    private String description;
    private double price;
    private ProductCategory category;
    private ProductCondition condition;

    public BaseProduct(Builder<?> b) {
        this.name = b.name;
        this.description = b.description;
        this.price = b.price;
        this.category = b.category;
        this.condition = b.condition;
    }

    // Tydzień 2, Wzorzec Builder 1
    // Implementacja wzorca Builder dla produktów, z domyślnymi wartościami i generycznym typowaniem, umożliwiającym łatwe tworzenie różnych typów produktów z różnymi właściwościami.
    public abstract static class Builder<T extends Builder<T>> {
        private String name = "Default Name";
        private String description = "Default Description";
        private double price = 0.0;
        private ProductCategory category = ProductCategory.OTHER;
        private ProductCondition condition = ProductCondition.NONE;

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        public T setPrice(double price) {
            this.price = price;
            return self();
        }

        public T setCategory(ProductCategory category) {
            this.category = category;
            return self();
        }

        public T setCondition(ProductCondition condition) {
            this.condition = condition;
            return self();
        }
        protected abstract T self();
        public abstract BaseProduct build();
    }
    // Koniec, Tydzień 2, Wzorzec Builder 1

    // Tydzień 2, Wzorzec Prototype 1
    @Override
    public BaseProduct clone() throws CloneNotSupportedException {
        try {
            return (BaseProduct) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    // Koniec, Tydzień 2, Wzorzec Prototype 1

    public String toString(){
        return "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "Price: " + price + "\n" +
                "Category: " + category + "\n" +
                "Condition: " + condition;
    }

    @Override
    public double getPrice(){
        return price;
    };

    @Override
    public String getItemName(){
        return name;
    };
}
