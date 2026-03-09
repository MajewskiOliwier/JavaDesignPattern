package allnew.okk.product.model;

import lombok.Getter;
import lombok.Setter;

// Bazowa klasa produktu, zawierająca wspólne właściwości i metody dla różnych typów produktów
@Getter
@Setter
public abstract class BaseProduct  implements Cloneable{
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

    // Bazowa klasa Builder, z domyślnymi wartościami dla wszystkich właściwości
    // Generyczna, aby umożliwić poprawne wykorzystanie w klasach dziedziczących
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

    // Implementacja metody clone() do tworzenia kopii obiektu
    @Override
    public BaseProduct clone() throws CloneNotSupportedException {
        try {
            return (BaseProduct) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String toString(){
        return "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "Price: " + price + "\n" +
                "Category: " + category + "\n" +
                "Condition: " + condition;
    }
}
