package allnew.okk.basket.Command;

import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.basket.composite.ShoppingBasket;

// Week 5, Pattern Command 1 Oliwier Majewski
// This commands adds a single element to the Basket
public class AddToBasketCommand implements  BasketCommand{
    private final ShoppingBasket basket;
    private final PurchasableItem item;

    public AddToBasketCommand(ShoppingBasket basket, PurchasableItem item) {
        this.basket = basket;
        this.item = item;
    }

    @Override
    public void Execute() {
        basket.addItem(item);
    }

    @Override
    public void undo() {
        basket.removeItem(item);
    }
}
// End Week 5, Pattern Command 1 Oliwier Majewski
