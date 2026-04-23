package allnew.okk.product.repository;
import allnew.okk.product.model.BaseProduct;

// Week 8, Dependency Inversion Principle Marciniuk
public abstract class AbstractProductRepository implements IProductRepository {
    protected abstract String generateNewId();
}
// End Week 8, Dependency Inversion Principle Marciniuk