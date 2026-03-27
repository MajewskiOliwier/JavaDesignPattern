package allnew.okk.shop.interpreter;

import allnew.okk.shop.model.BaseShop;

// Week 5, Pattern Interpreter 1
// Core interface for the Interpreter pattern.
// Evaluates if a given shop meets specific search criteria.
public interface ShopSearchExpression {
    boolean evaluate(BaseShop shop);
}
// End Week 5, Pattern Interpreter 1