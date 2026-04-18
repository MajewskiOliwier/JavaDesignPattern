package allnew.okk.product.model;

// Week 7, Open-Closed Principle (OCP), abstraction
public interface ProductExporter<T> {
    T export(BaseProduct product);
}
