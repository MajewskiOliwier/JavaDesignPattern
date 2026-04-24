package allnew.okk.shop.template;
// Week 9 - Maintaining Clean Code Principles

// Week 8 - Liskov Substitution Principle 2 (Derived Class)
// Overrides only protected implementation methods, leaving the external
// behavior of the report generation algorithm unchanged, allowing for interchangeability.

// Week 6, Pattern Template 3
// Concrete implementation that formats and exports the report as a CSV spreadsheet.
public class CsvShopReport extends ShopReportTemplate {

    private static final String LOG_FORMATTING = "[CSV] Formatowanie surowych danych do wartości oddzielonych przecinkami...";
    private static final String LOG_EXPORTING = "[CSV] Eksportowanie danych do 'report.csv':\n";

    private static final String CSV_HEADER = "Klucz,Wartość\n";
    private static final String OLD_DELIMITER_PIPE = " | ";
    private static final String NEW_DELIMITER_NEWLINE = "\n";
    private static final String OLD_DELIMITER_COLON = ": ";
    private static final String NEW_DELIMITER_COMMA = ",";

    @Override
    protected String formatReport(String rawData) {
        validateDataString(rawData);
        System.out.println(LOG_FORMATTING);
        return buildCsvStructure(rawData);
    }

    private String buildCsvStructure(String rawData) {
        String swappedPipes = rawData.replace(OLD_DELIMITER_PIPE, NEW_DELIMITER_NEWLINE);
        String finalizedFormat = swappedPipes.replace(OLD_DELIMITER_COLON, NEW_DELIMITER_COMMA);
        return CSV_HEADER + finalizedFormat;
    }

    @Override
    protected void exportReport(String content) {
        validateDataString(content);
        System.out.println(LOG_EXPORTING + content);
    }

    private void validateDataString(String data) {
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Dane w raporcie CSV nie mogą być puste.");
        }
    }
}
// End Week 6, Pattern Template 3