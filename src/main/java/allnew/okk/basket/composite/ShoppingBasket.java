package allnew.okk.basket.composite;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.Iterator.BasketIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Week 3, Pattern Composite Oliwier Majewski
public class ShoppingBasket implements PurchasableItem, Iterable<PurchasableItem>{
    List<PurchasableItem> items = new ArrayList<>();

    @Override
    public double getPrice() {
        double totalPrice = 0;

        for(PurchasableItem item : items){
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    //Week 5, Pattern Iterator Oliwier Majewski
    @Override
    public Iterator<PurchasableItem> iterator() {
        return new BasketIterator(items);
    }
    //End Week 5, Pattern Iterator Oliwier Majewski

    @Override
    public String getItemName() {
        return "";
    }

    @Override
    public String getSellerName() {
        return "";
    }

    @Override
    public String getSellerID() {
        return "";
    }

    @Override
    public AccountDisplayable getSellerAccount() {
        return null;
    }

    @Override
    public List<PurchasableItem> getChildren() {
        return items;
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
            AccountDisplayable sellerAccount = sellerProducts.getFirst().getSellerAccount();

            SellerBasket group = new SellerBasket(sellerID,sellerName,sellerAccount);
            sellerProducts.forEach(group::addItem);
            items.add(group);
        }
    }

    public void addItem(PurchasableItem item) {
        items.add(item);  // Products moved into this list
    }
}
//End Week 3, Pattern Composite Oliwier Majewski