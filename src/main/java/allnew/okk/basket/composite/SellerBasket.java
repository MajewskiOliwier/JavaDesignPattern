package allnew.okk.basket.composite;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.Visitor.BasketVisitor;

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
    public void setPrice(double newPrice) {
        // Not applicable for a seller basket, as it is a collection of items with their own prices.
        // The total price is calculated based on the prices of the individual items.
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

    //Week 6, Pattern Visitor Oliwier Majewski
    @Override
    public void accept(BasketVisitor visitor) {
        visitor.visit(this);
        for (PurchasableItem item : sellerItems) {
            item.accept(visitor);
        }
    }
    //End Week 6, Pattern Visitor Oliwier Majewski


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
