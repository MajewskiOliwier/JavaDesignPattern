// Week 4, Pattern Iterator 1 Oliwier Majewski
// Iterator that walks the top-level items of ShoppingBasket
package allnew.okk.basket.Iterator;

import allnew.okk.basket.composite.PurchasableItem;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BasketIterator implements Iterator<PurchasableItem> {
    private final List<PurchasableItem> items;
    private int currentIndex = 0;

    public BasketIterator(List<PurchasableItem> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < items.size();
    }

    @Override
    public PurchasableItem next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more items in basket");
        }
        return items.get(currentIndex++);
    }
}
// End Week 4, Pattern Iterator 1 Oliwier Majewski