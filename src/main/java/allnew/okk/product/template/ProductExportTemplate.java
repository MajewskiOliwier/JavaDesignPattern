package allnew.okk.product.template;

import allnew.okk.product.model.BaseProduct;

// Week 6 pattern template Marciniuk
public abstract class ProductExportTemplate {
    public final String export(BaseProduct product) {
        System.out.println("--- Starting export for: " + product.getName() + " ---");

        String rawData     = gatherData(product);
        String formatted   = formatData(rawData);
        exportOutput(formatted);

        System.out.println("--- Export finished ---");
        return formatted;
    }

    private String gatherData(BaseProduct product) {
        return "name=" + product.getName()
                + ";price=" + product.getPrice()
                + ";category=" + product.getCategory()
                + ";condition=" + product.getCondition();
    }

    protected abstract String formatData(String rawData);
    protected abstract void exportOutput(String content);
}
// End Week 6 pattern template Marciniuk
