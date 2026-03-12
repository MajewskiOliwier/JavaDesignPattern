package allnew.okk.product.Decorator;

import allnew.okk.basket.composite.PurchasableItem;

//Tydzień 3, Wzorzec Dekorator 1
//Jest to klasa abstrakcyjna pozwalająca na implementacje wzorca dekorator
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
//Koniec Tydzień 3, Wzorzec Dekorator 1