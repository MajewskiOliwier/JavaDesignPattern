package allnew.okk.product.Decorator;

import allnew.okk.basket.Visitor.BasketVisitor;
import allnew.okk.basket.composite.PurchasableItem;

// Week 3, Patter Decorator 1
// Jest to Klasa dziedzicząca po ProductDecorator która pozwala jej utworzenie z przedmiotu implementującego interfejsie PurchasableItem
// Klasa ta zwiększa cene oraz aktualizuje nazwe produktu po czym nadpisuje w koszyku produkt bazowy
public class ExtraDeliveryInsuranceDecorator extends ProductDecorator{
    private float insurancePrice = 15f;
    private String insuranceNameExtension = " + Gift Wrap";

    public ExtraDeliveryInsuranceDecorator(PurchasableItem wrappedItem) {
        super(wrappedItem);
    }

    @Override
    public void setPrice(double newPrice) {
        // Ustawienie ceny bazowego produktu, a koszt ubezpieczenia jest dodawany w metodzie getPrice.
        wrappedItem.setPrice(newPrice);
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice() + insurancePrice;
    }

    @Override
    public String getItemName(){
        return wrappedItem.getItemName() + insuranceNameExtension;
    };

    //Week 6, Pattern Visitor Oliwier Majewski
    @Override
    public void accept(BasketVisitor visitor) {
        wrappedItem.accept(visitor);
    }
    //End Week 6, Pattern Visitor Oliwier Majewski
}
//End Week 3, Pattern Decorator 1
