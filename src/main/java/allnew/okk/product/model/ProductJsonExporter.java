package allnew.okk.product.model;


// Week 7, Open-Closed Principle (OCP), implementation, jakub marciniuk
public class ProductJsonExporter implements ProductExporter<String> {

    @Override
    public String export(BaseProduct product) {
        return String.format(
            "{ \"id\": \"%s\", \"name\": \"%s\", \"price\": %.2f }",
            product.getID(),
            product.getName(),
            product.getPrice()
        );
    }
}
// End Week 6, Open-Closed Principle, implementation, jakub marciniuk