package allnew.okk.shop.interpreter;

import allnew.okk.shop.flyweight.ShopCategory;
import allnew.okk.shop.model.BaseShop;

// Week 9 - Maintaining Clean Code Principles

// Week 5, Pattern Interpreter 2
// Terminal expression that checks if a shop belongs to a specific category.
public class ShopCategoryExpression implements ShopSearchExpression {
    private final ShopCategory category;

    public ShopCategoryExpression(ShopCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Szukana kategoria nie może być pusta.");
        }
        this.category = category;
    }

    @Override
    public boolean evaluate(BaseShop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Sklep do oceny filtru nie może być pusty.");
        }
        return shop.getCategory() == category;
    }
}
// End Week 5, Pattern Interpreter 2