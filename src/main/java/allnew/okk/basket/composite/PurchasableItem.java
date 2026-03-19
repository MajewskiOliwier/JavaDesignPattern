package allnew.okk.basket.composite;

import allnew.okk.account.Adapter.AccountDisplayable;

import java.util.Collections;
import java.util.List;

//Week 3, Pattern Composite Oliwier Majewski
public interface PurchasableItem {
    public double getPrice();
    public String getItemName();
    public String getSellerName();
    public String getSellerID();
    public AccountDisplayable getSellerAccount();
    public default List<PurchasableItem> getChildren(){
        return Collections.emptyList();
    }
}
//End Week 3, Pattern Composite