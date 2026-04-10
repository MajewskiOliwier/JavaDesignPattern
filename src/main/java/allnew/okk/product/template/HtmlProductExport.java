package allnew.okk.product.template;

// Week 6, pattern template method Marciniuk
public class HtmlProductExport extends ProductExportTemplate {

    @Override
    protected String formatData(String rawData) {
        String rows = rawData.replace(";", "</td></tr><tr><td>")
                .replace("=", "</td><td>");
        return "<table><tr><td>" + rows + "</td></tr></table>";
    }

    @Override
    protected void exportOutput(String content) {
        System.out.println("HtmlProductExport: saving to product.html:\n" + content);
    }
}
// End Week 6, pattern template method Marciniuk