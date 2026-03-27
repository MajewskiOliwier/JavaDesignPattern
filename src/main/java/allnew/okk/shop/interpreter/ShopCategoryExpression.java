package allnew.okk.shop.interpreter;

import allnew.okk.shop.flyweight.ShopCategory;
import allnew.okk.shop.model.BaseShop;

// Week 5, Pattern Interpreter 2
// Terminal expression that checks if a shop belongs to a specific category.
public class ShopCategoryExpression implements ShopSearchExpression {
    private final ShopCategory category;

    public ShopCategoryExpression(ShopCategory category) {
        this.category = category;
    }

    @Override
    public boolean evaluate(BaseShop shop) {
        return shop.getCategory() == category;
    }
}
// End Week 5, Pattern Interpreter 2