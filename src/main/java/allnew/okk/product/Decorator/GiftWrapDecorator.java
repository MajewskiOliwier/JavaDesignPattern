package allnew.okk.product.Decorator;

import allnew.okk.basket.composite.PurchasableItem;

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
