package allnew.okk.product.repository;
import allnew.okk.product.model.BaseProduct;
import java.util.List;

// Week 8, Dependency Inversion Principle Marciniuk
public interface IProductRepository {
    void addProduct(BaseProduct product);
    BaseProduct getProduct(String id);
    List<BaseProduct> getAllProducts();
    boolean removeProduct(String id);
}
// End Week 8, Dependency Inversion Principle Marciniuk