package allnew.okk.basket.Interpreter;

import allnew.okk.basket.composite.PurchasableItem;

// Week 5, Pattern Interpreter 1 Oliwier Majewski
// Evaluates item price against a threshold using operators like GreaterThan (GT) and others
public class PriceExpression implements BasketInterpreterExpression{
    private final double threshold;
    private final ComparisonOperator operator;

    public PriceExpression(double threshold, ComparisonOperator operator) {
        this.threshold = threshold;
        this.operator = operator;
    }

    //This method will evaluate if the items price is Greater,Lower or Equal based on the operator passed in the constructor
    @Override
    public boolean evaluate(PurchasableItem item) {
        return switch(operator){
            case GT -> item.getPrice() > threshold;
            case LT -> item.getPrice() < threshold;
            case EQ -> item.getPrice() == threshold;
        };
    }
}
// End Week 5, Pattern Interpreter 1 Oliwier Majewski