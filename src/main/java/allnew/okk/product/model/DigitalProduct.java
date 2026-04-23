package allnew.okk.product.model;

import allnew.okk.basket.Visitor.BasketVisitor;
import allnew.okk.product.visitor.ProductVisitor;

// Week 8, Liskov Substitution Principle Marciniuk
public class DigitalProduct extends BaseProduct {
    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }

    public DigitalProduct(Builder<?> b) {
        super(b);
    }

    @Override
    public String getSellerName() {
        return "";
    }

    @Override
    public String getSellerID() {
        return "";
    }

    @Override
    public void accept(BasketVisitor visitor) {
        visitor.visit(this);
    }


    public static class DigitalBuilder extends Builder<DigitalBuilder> {
        @Override
        protected DigitalBuilder self() { return this; }
        @Override
        public BaseProduct build() { return new DigitalProduct(this); }
    }
}
// End Week 8, Liskov Substitution Principle Marciniuk