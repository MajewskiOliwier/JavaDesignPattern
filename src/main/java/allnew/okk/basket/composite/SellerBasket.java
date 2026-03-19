package allnew.okk.basket.composite;

import allnew.okk.account.Adapter.AccountDisplayable;

import java.util.ArrayList;
import java.util.List;

//Week 3, Pattern Composite Oliwier Majewski
public class SellerBasket implements PurchasableItem{
    private List<PurchasableItem> sellerItems = new ArrayList<>();
    private String sellerID;
    private String sellerName;
    private AccountDisplayable sellerAccount;

    public void addItem(PurchasableItem item) {
        sellerItems.add(item);
    }

    public SellerBasket(String sellerID, String sellerName, AccountDisplayable sellerAccount){
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.sellerAccount = sellerAccount;
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

    @Override
    public AccountDisplayable getSellerAccount() {
        return sellerAccount;
    }
}
//End Week 3, Pattern Composite Oliwier Majewski
