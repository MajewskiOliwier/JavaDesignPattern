package allnew.okk.basket.composite;

import java.util.ArrayList;
import java.util.List;

public class SellerBasket implements PurchasableItem{
    private List<PurchasableItem> sellerItems = new ArrayList<>();
    private String sellerID;
    private String sellerName;

    public void addItem(PurchasableItem item) {
        sellerItems.add(item);
    }

    public SellerBasket(String sellerID, String sellerName){
        this.sellerID = sellerID;
        this.sellerName = sellerName;
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;

        for (PurchasableItem item : sellerItems){
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    @Override
    public List<PurchasableItem> getChildren(){
        return sellerItems;
    }


    @Override
    public String getItemName() {
        return "";
    }

    @Override
    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String getSellerID() {
        return sellerID;
    }
}
