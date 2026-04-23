package allnew.okk.product.Decorator;

import allnew.okk.basket.Visitor.BasketVisitor;
import allnew.okk.basket.composite.PurchasableItem;

// Week 3, Pattern Decorator 1 Oliwier Majewski
// Jest to Klasa dziedzicząca po ProductDecorator która pozwala jej utworzenie z przedmiotu dziedziczącego po interfejsie PurchasableItem
// Klasa ta zwiększa cene oraz aktualizuje nazwe produktu po czym nadpisuje w koszyku produkt bazowy
public class GiftWrapDecorator extends ProductDecorator{
    private float giftWrapPrice = 5f;
    private String giftNameExtension = " + Gift Wrap";

    public GiftWrapDecorator(PurchasableItem wrappedItem) {
        super(wrappedItem);
    }

    @Override
    public void setPrice(double newPrice) {
        giftWrapPrice = (float)newPrice;
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice() + giftWrapPrice;
    }

    @Override
    public String getItemName(){
        return wrappedItem.getItemName() + giftNameExtension;
    };

    //Week 6, Pattern Visitor Oliwier Majewski
    @Override
    public void accept(BasketVisitor visitor) {
        wrappedItem.accept(visitor);
    }
    //End Week 6, Pattern Visitor Oliwier Majewski
}
//End Week 3, Pattern Decorator 1