package allnew.okk.product.interpreter;

import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.model.ProductCategory;

// week 5, pattern interpreter 3 Jakub Marciniuk
public class ProductCategoryExpression implements ProductSearchExpression {
    private final ProductCategory category;
    public ProductCategoryExpression(ProductCategory category) {
        this.category = category;
    }

    @Override
    public boolean evaluate(BaseProduct product) {
        return product.getCategory() == category;
    }
}
// end of week 5, pattern interpreter 3