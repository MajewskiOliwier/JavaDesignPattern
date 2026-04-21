package allnew.okk.shop.template;


// Week 8 - Liskov Substitution Principle 2 (Klasa pochodna)
// Nadpisuje jedynie chronione metody implementacyjne, nie zmieniając zewnętrznego
// zachowania algorytmu generowania raportu, co pozwala na jej zamienne użycie.

// Week 6, Pattern Template 3
// Concrete implementation that formats and exports the report as a CSV spreadsheet.
public class CsvShopReport extends ShopReportTemplate {

    @Override
    protected String formatReport(String rawData) {
        System.out.println("CsvReport: Formatting raw data into comma-separated values...");
        // Prosta zamiana naszego stringa na format CSV dla potrzeb wizualizacji
        return "Key,Value\n" + rawData.replace(" | ", "\n").replace(": ", ",");
    }

    @Override
    protected void exportReport(String content) {
        System.out.println("CsvReport: Exporting data to 'report.csv':\n" + content);
    }
}
// End Week 6, Pattern Template 3