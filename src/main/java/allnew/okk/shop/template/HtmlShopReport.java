package allnew.okk.shop.template;
// Week 9 - Maintaining Clean Code Principles

// Week 8 - Liskov Substitution Principle 2 (Derived Class)
// Overrides only protected implementation methods, leaving the external
// behavior of the report generation algorithm unchanged, allowing for interchangeability.

// Week 6, Pattern Template 2
// Concrete implementation that formats and exports the report as an HTML document.
public class HtmlShopReport extends ShopReportTemplate {

    private static final String LOG_FORMATTING = "[HTML] Formatowanie surowych danych w tagi HTML...";
    private static final String LOG_EXPORTING = "[HTML] Zapisywanie pliku jako 'report.html' -> ";

    private static final String HTML_PREFIX = "<html><body><h1>Szczegóły Sklepu</h1><p>";
    private static final String HTML_SUFFIX = "</p></body></html>";

    @Override
    protected String formatReport(String rawData) {
        validateDataString(rawData);
        System.out.println(LOG_FORMATTING);
        return HTML_PREFIX + rawData + HTML_SUFFIX;
    }

    @Override
    protected void exportReport(String content) {
        validateDataString(content);
        System.out.println(LOG_EXPORTING + content);
    }

    private void validateDataString(String data) {
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Dane w raporcie HTML nie mogą być puste.");
        }
    }
}
// End Week 6, Pattern Template 2