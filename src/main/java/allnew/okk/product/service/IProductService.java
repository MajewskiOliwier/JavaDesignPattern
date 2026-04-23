package allnew.okk.product.service;

// Week 8, Dependency Inversion Principle Marciniuk
public interface IProductService {
    void duplicateProduct(String productId, DuplicationModifier modifier) throws Exception;
}
// End Week 8, Dependency Inversion Principle Marciniuk