package allnew.okk.basket.Interpreter;

import allnew.okk.basket.composite.PurchasableItem;

// Week 5, Pattern Interpreter 1 Oliwier Majewski
//Interface that will allow implementation Interpreter pattern for the Basket
public interface BasketInterpreterExpression {
    boolean evaluate(PurchasableItem item);
}
//End Week 5, Pattern Interpreter 1 Oliwier Majewski
