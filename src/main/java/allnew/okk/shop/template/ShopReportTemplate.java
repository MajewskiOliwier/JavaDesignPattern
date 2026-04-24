package allnew.okk.shop.template;

import allnew.okk.shop.model.BaseShop;
// Week 9 - Maintaining Clean Code Principles
// Week 8 - Liskov Substitution Principle 2
// Base class for report generators. Instances can be replaced with specific implementations (HTML/CSV).

// Week 6, Pattern Template 1
// Abstract class defining the skeleton of the report generation algorithm.
public abstract class ShopReportTemplate {

    private static final String LOG_START_PREFIX = "--- Rozpoczęto generowanie raportu dla ";
    private static final String LOG_START_SUFFIX = " ---";
    private static final String LOG_FINISH = "--- Generowanie raportu zakończone ---";
    private static final String LOG_GATHERING = "[SZABLON] Zbieranie surowych danych z bazy...";
    private static final String DATA_FORMAT = "Sklep: %s | Kategoria: %s";

    public final void generateReport(BaseShop shop) {
        validateShop(shop);
        logStart(shop);
        processReportData(shop);
        logFinish();
    }

    private void validateShop(BaseShop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Sklep docelowy raportu nie może być pusty.");
        }
    }

    private void logStart(BaseShop shop) {
        System.out.println(LOG_START_PREFIX + shop.getName() + LOG_START_SUFFIX);
    }

    private void processReportData(BaseShop shop) {
        String rawData = gatherData(shop);
        String formattedContent = formatReport(rawData);
        exportReport(formattedContent);
    }

    private void logFinish() {
        System.out.println(LOG_FINISH);
    }

    private String gatherData(BaseShop shop) {
        System.out.println(LOG_GATHERING);
        return String.format(DATA_FORMAT, shop.getName(), shop.getCategory());
    }

    // Abstract steps that MUST be implemented by specific report types
    protected abstract String formatReport(String rawData);
    protected abstract void exportReport(String content);
}
// End Week 6, Pattern Template 1