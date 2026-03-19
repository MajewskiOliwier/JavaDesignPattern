package allnew.okk.basket.Interpreter;

import allnew.okk.basket.composite.PurchasableItem;

// Week 5, Pattern Interpreter 1 Oliwier Majewski
public class AndExpression implements BasketInterpreterExpression {
    private final BasketInterpreterExpression leftExpression;
    private final BasketInterpreterExpression rightExpression;

    public AndExpression(BasketInterpreterExpression leftExpression, BasketInterpreterExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    //This method will evaluate if the previous and the following expression is positively evaluated
    @Override
    public boolean evaluate(PurchasableItem item) {
        return leftExpression.evaluate(item) && rightExpression.evaluate(item);
    }
}
//End Week 5, Pattern Interpreter 1 Oliwier Majewski