package allnew.okk.shop.interpreter;

import allnew.okk.shop.model.BaseShop;

// Week 9 - Maintaining Clean Code Principles

// Week 5, Pattern Interpreter 4
// Non-terminal expression that combines two other expressions with a logical AND.
public class ShopAndExpression implements ShopSearchExpression {
    private final ShopSearchExpression left;
    private final ShopSearchExpression right;

    public ShopAndExpression(ShopSearchExpression leftExpression, ShopSearchExpression rightExpression) {
        if (leftExpression == null || rightExpression == null) {
            throw new IllegalArgumentException("Wyrażenia filtrujące nie mogą być puste.");
        }
        this.left = leftExpression;
        this.right = rightExpression;
    }

    @Override
    public boolean evaluate(BaseShop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Sklep do oceny filtru nie może być pusty.");
        }
        return left.evaluate(shop) && right.evaluate(shop);
    }
}
// End Week 5, Pattern Interpreter 4