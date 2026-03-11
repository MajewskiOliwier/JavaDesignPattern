package allnew.okk.product.Decorator;

import allnew.okk.basket.composite.PurchasableItem;

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
