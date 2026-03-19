package allnew.okk.basket.Interpreter;

import allnew.okk.basket.composite.PurchasableItem;

// Week 5, Pattern Interpreter 1 Oliwier Majewski
public class ItemNameContainExpression implements BasketInterpreterExpression {
    private final String itemNameSubstring;

    public ItemNameContainExpression(String itemNameSubstring){
        this.itemNameSubstring = itemNameSubstring;
    }

    //This method will evaluate if items' name contain the substring passed in constructor
    @Override
    public boolean evaluate(PurchasableItem item) {
        return item.getItemName().toLowerCase().contains(itemNameSubstring.toLowerCase());
    }
}
//End Week 5, Pattern Interpreter 1 Oliwier Majewski