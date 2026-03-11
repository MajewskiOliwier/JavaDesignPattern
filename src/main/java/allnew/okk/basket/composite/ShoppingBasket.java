package allnew.okk.basket.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingBasket implements PurchasableItem{
    List<PurchasableItem> items = new ArrayList<>();

    @Override
    public double getPrice() {
        double totalPrice = 0;

        for(PurchasableItem item : items){
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    @Override
    public String getItemName() {
        return "";
    }

    @Override
    public String getSellerName() {
        return null;
    }

    @Override
    public String getSellerID() {
        return null;
    }

    public void OrganizeBySeller(){
        Map<String, List<PurchasableItem>> groupedBySeller = items.stream()
                .filter(item -> !(item instanceof SellerBasket))
                .collect(Collectors.groupingBy(PurchasableItem::getSellerID));

        items.clear();

        for (Map.Entry<String, List<PurchasableItem>> entry : groupedBySeller.entrySet()){
            String sellerID = entry.getKey();
            List<PurchasableItem> sellerProducts = entry.getValue();

            String sellerName = sellerProducts.getFirst().getSellerName();

            SellerBasket group = new SellerBasket(sellerName, sellerID);
            sellerProducts.forEach(group::addItem);
            items.add(group);
        }
    }

    public void addItem(PurchasableItem item) {
        items.add(item);  // Products moved into this list
    }
}
