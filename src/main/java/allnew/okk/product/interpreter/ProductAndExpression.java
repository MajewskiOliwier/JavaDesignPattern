package allnew.okk.product.interpreter;

// week 5, pattern interpreter 4 Jakub Marciniuk
public class ProductAndExpression implements ProductSearchExpression {
    private final ProductSearchExpression left;
    private final ProductSearchExpression right;

    public ProductAndExpression(ProductSearchExpression left, ProductSearchExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(allnew.okk.product.model.BaseProduct product) {
        return left.evaluate(product) && right.evaluate(product);
    }
}
// end of week 5, pattern interpreter 4
