package allnew.okk.basket.Command;

import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.basket.composite.ShoppingBasket;

// Week 5, Pattern Command 1 Oliwier Majewski
// This command willl remove a singular element from the basket
public class RemoveLastProductFromBasketCommand implements  BasketCommand{
    private final ShoppingBasket basket;
    private final PurchasableItem item;

    public RemoveLastProductFromBasketCommand(ShoppingBasket basket, PurchasableItem item) {
        this.basket = basket;
        this.item = item;
    }

    @Override
    public void Execute() {
        basket.removeItem(item);
    }

    @Override
    public void undo() {
        basket.addItem(item);
    }
}
//End Week 5, Pattern Command 1 Oliwier Majewski
