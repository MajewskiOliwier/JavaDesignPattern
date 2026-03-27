package allnew.okk.shop.interpreter;

import allnew.okk.shop.model.BaseShop;

// Week 5, Pattern Interpreter 4
// Non-terminal expression that combines two other expressions with a logical AND.
public class ShopAndExpression implements ShopSearchExpression {
    private final ShopSearchExpression left;
    private final ShopSearchExpression right;

    public ShopAndExpression(ShopSearchExpression left, ShopSearchExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(BaseShop shop) {
        // Obie strony muszą być prawdziwe (AND)
        return left.evaluate(shop) && right.evaluate(shop);
    }
}
// End Week 5, Pattern Interpreter 4