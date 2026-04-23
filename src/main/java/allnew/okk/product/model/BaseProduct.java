package allnew.okk.product.model;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.product.bridge.TaxPolicy;
import allnew.okk.product.memento.ProductPriceMemento;
import allnew.okk.product.observer.ProductObserver;
import allnew.okk.product.state.AvailableState;
import allnew.okk.product.state.DiscontinuedState;
import allnew.okk.product.state.OutOfStockState;
import allnew.okk.product.state.ProductState;
import allnew.okk.product.strategy.ProductPricingStrategy;
import allnew.okk.product.strategy.StandardPricingStrategy;
import allnew.okk.product.visitor.ProductVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Bazowa klasa produktu, zawierająca wspólne właściwości i metody dla różnych typów produktów
@Getter
@Setter
public abstract class BaseProduct  implements Cloneable, PurchasableItem {
    private String name;
    private String description;
    private double price;
    private ProductCategory category;
    private ProductCondition condition;
    private TaxPolicy taxPolicy;
    private AccountDisplayable accountDisplayable;
    private Integer ID;
    private final List<ProductObserver> observers = new ArrayList<>();
    private ProductState productState = new AvailableState();
    private ProductPricingStrategy pricingStrategy;

    // week 7 open-closed (OCP), data control Jakub Marciniuk
    private final Map<String, String> additionalProperties = new HashMap<>();
    public void setAdditionalProperty(String key, String value) {
        additionalProperties.put(key, value);
    }

    public String getAdditionalProperty(String key) {
        return additionalProperties.get(key);
    }
    // end week 7 open-closed (OCP), data control Jakub Marciniuk


    public abstract void accept(ProductVisitor visitor);
    public BaseProduct(Builder<?> b) {
        this.name = b.name;
        this.description = b.description;
        this.price = b.price;
        this.category = b.category;
        this.condition = b.condition;
        this.taxPolicy = b.taxPolicy;
        this.accountDisplayable = b.accountDisplayable;
        this.ID = b.ID;
    }
    // Week 6, Pattern Strategy Marciniuk
    public double getFinalPrice() {
        if (pricingStrategy != null) {
            return pricingStrategy.calculateFinalPrice(getPrice());
        }
        return getPrice();
    }
    // End Week 6, Pattern Strategy Marciniuk
    // Week 6, Pattern State Marciniuk
    public void markAvailable()     { this.productState = new AvailableState(); }
    public void markOutOfStock()    { this.productState = new OutOfStockState(); }
    public void markDiscontinued()  { this.productState = new DiscontinuedState(); }

    public boolean canBePurchased() { return productState.canBePurchased(); }
    public String getStateName()    { return productState.getStateName(); }
    // End Week 6, Pattern State Marciniuk

    // Week 6, Pattern Observer Marciniuk
    public void addObserver(ProductObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProductObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void setPrice(double newPrice) {
        double oldPrice = this.price;
        this.price = newPrice;
        for (ProductObserver observer : observers) {
            observer.onPriceChanged(this.name, oldPrice, newPrice);
        }
    }
    // End Week 6, Pattern Observer Marciniuk

    public Integer getID() {
        return ID != null ? ID : null;
    }

    // Tydzień 2, Wzorzec Builder 1
    // Implementacja wzorca Builder dla produktów, z domyślnymi wartościami i generycznym typowaniem, umożliwiającym łatwe tworzenie różnych typów produktów z różnymi właściwościami.
    public abstract static class Builder<T extends Builder<T>> {
        private static final double DEFAULT_PRICE = 0.0;
        private static final String DEFAULT_NAME = "Default Name";
        private static final String DEFAULT_DESC = "Default Description";

        private String name = DEFAULT_NAME;
        private String description = DEFAULT_DESC;
        private double price = DEFAULT_PRICE;
        private ProductCategory category = ProductCategory.OTHER;
        private ProductCondition condition = ProductCondition.NONE;
        private TaxPolicy taxPolicy;
        private AccountDisplayable accountDisplayable;
        private Integer ID;
        private ProductPricingStrategy pricingStrategy = new StandardPricingStrategy();


        public T setPricingStrategy(ProductPricingStrategy strategy) {
            this.pricingStrategy = strategy;
            return self();
        }
        public T setID(Integer ID) {
            this.ID = ID;
            return self();
        }
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

        public T setTaxPolicy(TaxPolicy taxPolicy) {
            this.taxPolicy = taxPolicy;
            return self();
        }

        public T setAccountDisplayable(AccountDisplayable accountDisplayable){
            this.accountDisplayable = accountDisplayable;
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

    // week 7 Single Responsibility Principle SRP, Marciniuk
    // Usunięto metode toString, za to odpowiada teraz implementacje ProductExporter
    /*public String toString(){
        return "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "Price: " + price + "\n" +
                "Category: " + category + "\n" +
                "Condition: " + condition;
    }*/
    // End week 7 Single Responsibility Principle SRP, Marciniuk

    //week 5, pattern Memento 2 Jakub Marciniuk
    public ProductPriceMemento saveMemento() {
        return new ProductPriceMemento(price);
    }

    public void restoreMemento(ProductPriceMemento memento) {
        this.price = memento.getPrice();
    }
    //end week 5, pattern Memento 2

    @Override
    public double getPrice(){
        return price;
    };

    @Override
    public String getItemName(){
        return name;
    };

    @Override
    public AccountDisplayable getSellerAccount() {
        return accountDisplayable;
    }

    // Tydzień 3, Wzorzec Bridge 1
    public double getPriceWithTax() {
        if (taxPolicy != null) {
            return taxPolicy.calculateTotalPrice(price);
        }
        return price;
    }
    // Koniec Tydzień 3, Wzorzec Bridge 1
}
