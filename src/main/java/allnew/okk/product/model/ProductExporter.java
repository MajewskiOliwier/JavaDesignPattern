package allnew.okk.product.model;

// Week 6, Open-Closed Principle, abstraction
public interface ProductExporter<T> {
    T export(BaseProduct product);
}
