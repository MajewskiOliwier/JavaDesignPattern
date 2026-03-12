package allnew.okk.product.Decorator;

import allnew.okk.basket.composite.PurchasableItem;

// Tydzień 3, Wzorzec Dekorator 1
// Jest to Klasa dziedzicząca po ProductDecorator która pozwala jej utworzenie z przedmiotu dziedziczącego po interfejsie PurchasableItem
// Klasa ta zwiększa cene oraz aktualizuje nazwe produktu po czym nadpisuje w koszyku produkt bazowy
public class GiftWrapDecorator extends ProductDecorator{
    private float giftWrapPrice = 5f;
    private String giftNameExtension = " + Gift Wrap";

    public GiftWrapDecorator(PurchasableItem wrappedItem) {
        super(wrappedItem);
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice() + giftWrapPrice;
    }

    @Override
    public String getItemName(){
        return wrappedItem.getItemName() + giftNameExtension;
    };
}
//Koniec Tydzień 3, Wzorzec Dekorator 1