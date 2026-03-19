package allnew.okk.basket.Interpreter;

import allnew.okk.basket.composite.PurchasableItem;

// Week 5, Pattern Interpreter 1 Oliwier Majewski
public class SellerNameContainExpression implements BasketInterpreterExpression {
    private final String sellerNameSubstring;

    public SellerNameContainExpression(String sellerNameSubstring){
        this.sellerNameSubstring = sellerNameSubstring;
    }

    //This method will evaluate if sellers' name contain the substring passed in constructor
    @Override
    public boolean evaluate(PurchasableItem item) {
        return item.getSellerName().toLowerCase().contains(sellerNameSubstring.toLowerCase());
    }
}
//End Week 5, Pattern Interpreter 1 Oliwier Majewski