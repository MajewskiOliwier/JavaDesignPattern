package allnew.okk.product.service;
import allnew.okk.product.repository.IProductRepository;

// Week 8, Dependency Inversion Principle Marciniuk
public abstract class AbstractProductService implements IProductService {
    protected final IProductRepository repository;
    protected AbstractProductService(IProductRepository repository) {
        this.repository = repository;
    }
}
// End Week 8, Dependency Inversion Principle Marciniuk