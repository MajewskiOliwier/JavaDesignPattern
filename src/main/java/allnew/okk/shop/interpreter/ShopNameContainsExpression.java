package allnew.okk.shop.interpreter;

import allnew.okk.shop.model.BaseShop;

// Week 5, Pattern Interpreter 3
// Terminal expression that evaluates if a shop's name contains a given substring (case-insensitive).
public class ShopNameContainsExpression implements ShopSearchExpression {
    private final String substring;

    public ShopNameContainsExpression(String substring) {
        this.substring = substring;
    }

    @Override
    public boolean evaluate(BaseShop shop) {
        return shop.getName().toLowerCase().contains(substring.toLowerCase());
    }
}
// End Week 5, Pattern Interpreter 3