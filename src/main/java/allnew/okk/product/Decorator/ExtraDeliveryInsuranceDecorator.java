package allnew.okk.product.Decorator;

import allnew.okk.basket.composite.PurchasableItem;

// Tydzień 3, Wzorzec Dekorator 1
// Jest to Klasa dziedzicząca po ProductDecorator która pozwala jej utworzenie z przedmiotu implementującego interfejsie PurchasableItem
// Klasa ta zwiększa cene oraz aktualizuje nazwe produktu po czym nadpisuje w koszyku produkt bazowy
public class ExtraDeliveryInsuranceDecorator extends ProductDecorator{
    private float insurancePrice = 15f;
    private String insuranceNameExtension = " + Gift Wrap";

    public ExtraDeliveryInsuranceDecorator(PurchasableItem wrappedItem) {
        super(wrappedItem);
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice() + insurancePrice;
    }

    @Override
    public String getItemName(){
        return wrappedItem.getItemName() + insuranceNameExtension;
    };
}
//Koniec Tydzień 3, Wzorzec Dekorator 1
