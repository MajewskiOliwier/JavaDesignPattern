package allnew.okk.product.Decorator;

import allnew.okk.basket.composite.PurchasableItem;

//Week  3, Pattern Decorator 1  Oliwier Majewski
//This is an abstract class that allows for the implementation of the decorator pattern.
public abstract class ProductDecorator implements PurchasableItem {
    protected PurchasableItem wrappedItem;

    public ProductDecorator(PurchasableItem wrappedItem){
        this.wrappedItem = wrappedItem;
    }

    @Override
    public double getPrice() {
        return wrappedItem.getPrice();
    }

    @Override
    public String getItemName(){
        return wrappedItem.getItemName();
    };

    @Override
    public String getSellerName(){
        return wrappedItem.getSellerName();
    };

    @Override
    public String getSellerID(){
        return wrappedItem.getSellerID();
    };
}
//End Week 3, Pattern Decorator 1