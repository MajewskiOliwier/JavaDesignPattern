package allnew.okk.basket.composite;

import java.util.Collections;
import java.util.List;

public interface PurchasableItem {
    public double getPrice();
    public String getItemName();
    public String getSellerName();
    public String getSellerID();
    public default List<PurchasableItem> getChildren(){
        return Collections.emptyList();
    }
}
