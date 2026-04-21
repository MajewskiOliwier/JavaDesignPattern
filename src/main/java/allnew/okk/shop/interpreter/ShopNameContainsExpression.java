package allnew.okk.shop.interpreter;

import allnew.okk.shop.model.BaseShop;

// Week 9 - Maintaining Clean Code Principles

// Week 5, Pattern Interpreter 3
// Terminal expression that evaluates if a shop's name contains a given substring (case-insensitive).
public class ShopNameContainsExpression implements ShopSearchExpression {
    private final String substring;

    public ShopNameContainsExpression(String substring) {
        if (substring == null || substring.trim().isEmpty()) {
            throw new IllegalArgumentException("Szukana fraza nie może być pusta.");
        }
        this.substring = substring;
    }

    @Override
    public boolean evaluate(BaseShop shop) {
        if (shop == null || shop.getName() == null) {
            throw new IllegalArgumentException("Sklep do oceny lub jego nazwa nie może być pusta.");
        }
        return checkNameContains(shop.getName(), this.substring);
    }

    private boolean checkNameContains(String shopName, String query) {
        return shopName.toLowerCase().contains(query.toLowerCase());
    }
}
// End Week 5, Pattern Interpreter 3