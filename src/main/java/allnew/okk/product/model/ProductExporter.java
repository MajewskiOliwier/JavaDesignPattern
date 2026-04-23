package allnew.okk.product.model;

// Week 7, Open-Closed Principle (OCP), abstraction, jakub marciniuk
public interface ProductExporter<T> {
    T export(BaseProduct product);
}
// End Week 7, Open-Closed Principle (OCP), abstraction, jakub marciniuk
