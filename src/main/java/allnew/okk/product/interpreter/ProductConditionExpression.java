package allnew.okk.product.interpreter;

import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.model.ProductCondition;

// week 5, pattern interpreter 2 Jakub Marciniuk
public class ProductConditionExpression implements ProductSearchExpression{
    private final ProductCondition condition;

    public ProductConditionExpression(ProductCondition condition) {
        this.condition = condition;
    }

    @Override
    public boolean evaluate(BaseProduct product) {
        return product.getCondition() == condition;
    }
}
// end of week 5, pattern interpreter 2