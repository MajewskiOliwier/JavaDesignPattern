package allnew.okk.shop.template;

// Week 6, Pattern Template 2
// Concrete implementation that formats and exports the report as an HTML document.
public class HtmlShopReport extends ShopReportTemplate {

    @Override
    protected String formatReport(String rawData) {
        System.out.println("HtmlReport: Formatting raw data into HTML tags...");
        return "<html><body><h1>Shop Details</h1><p>" + rawData + "</p></body></html>";
    }

    @Override
    protected void exportReport(String content) {
        System.out.println("HtmlReport: Saving file as 'report.html' -> " + content);
    }
}
// End Week 6, Pattern Template 2