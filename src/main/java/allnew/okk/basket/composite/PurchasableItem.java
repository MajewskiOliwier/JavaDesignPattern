package allnew.okk.basket.composite;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.Visitor.BasketVisitor;

import java.util.Collections;
import java.util.List;

//Week 3, Pattern Composite Oliwier Majewski
public interface PurchasableItem {
    void setPrice(double newPrice);

    public double getPrice();
    public String getItemName();
    public String getSellerName();
    public String getSellerID();
    public AccountDisplayable getSellerAccount();
    public default List<PurchasableItem> getChildren(){
        return Collections.emptyList();
    }

    //Week 6, Pattern Visitor Oliwier Majewski
    void accept(BasketVisitor visitor);
    //End Week 6, Pattern Visitor Oliwier Majewski
}
//End Week 3, Pattern Composite