package allnew.okk.product.model;

import allnew.okk.basket.Visitor.BasketVisitor;
import allnew.okk.product.visitor.ProductVisitor;
import lombok.Getter;
import lombok.Setter;


// Klasa reprezentująca produkt prywatny, dziedzicząca po BaseProduct
@Getter
public class PrivateProduct extends BaseProduct {
    private final String sellerName;
    @Setter
    private String sellerContact;

    public PrivateProduct(Builder b) {
        super(b);
        this.sellerName = b.sellerName;
        this.sellerContact = b.sellerContact;
    }

    // Week 3, Pattern Composite 3
    @Override
    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String getSellerID() {
        return sellerName;
    }

    //Week 6, Pattern Visitor Oliwier Majewski
    //method that allows Visitor design patter to traverse the Composite baskets and in this case get this product
    @Override
    public void accept(BasketVisitor visitor) {
        visitor.visit(this);
    }
    //End Week 6, Pattern Visitor Oliwier Majewski

    // End Week 3, Pattern Composite 3

    // Week 2, Pattern  Builder 3
    // Implementacja wzorca Builder dla produktów
    public static class Builder extends BaseProduct.Builder<Builder> {
        private String sellerName;
        private String sellerContact;

        public Builder setSellerName(String sellerName) {
            this.sellerName = sellerName;
            return this;
        }

        public Builder setSellerContact(String sellerContact) {
            this.sellerContact = sellerContact;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public PrivateProduct build() {
            return new PrivateProduct(this);
        }
    }
    // End Week 2, Pattern  Builder 3
    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
    public Builder toBuilder() {
        return new Builder()
                .setID(getID())
                .setName(getName())
                .setDescription(getDescription())
                .setPrice(getPrice())
                .setCategory(getCategory())
                .setCondition(getCondition())
                .setSellerName(sellerName)
                .setSellerContact(sellerContact);
    }

    // Week 2, Pattern  Prototype 3
    @Override
    public PrivateProduct clone() {
        try {
            // Zrzutowany na PrivateProduct
            return (PrivateProduct) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    // Week 2, Pattern Prototype 3

    @Override
    public String toString() {
        return super.toString() + "\n" +
                ", sellerName: " + sellerName + "\n" +
                ", sellerContact: " + sellerContact;
    }
}

