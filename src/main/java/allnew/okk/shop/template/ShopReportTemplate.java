package allnew.okk.shop.template;

import allnew.okk.shop.model.BaseShop;

// Week 8 - Liskov Substitution Principle 2
// Klasa bazowa dla generatorów raportów. Jej instancje można zamieniać konkretnymi implementacjami (HTML/CSV).

// Week 6, Pattern Template 1
// Abstract class defining the skeleton of the report generation algorithm.
public abstract class ShopReportTemplate {

    // The template method defining the exact sequence of steps.
    // It is marked as 'final' so subclasses cannot change the algorithm's overarching structure.
    public final void generateReport(BaseShop shop) {
        System.out.println("--- Starting report generation for " + shop.getName() + " ---");

        String rawData = gatherData(shop);
        String formattedContent = formatReport(rawData);
        exportReport(formattedContent);

        System.out.println("--- Report generation finished ---");
    }

    // Common step implemented for all subclasses (doesn't need to be overridden)
    private String gatherData(BaseShop shop) {
        System.out.println("Template: Gathering raw data from the database...");
        return "Shop: " + shop.getName() + " | Category: " + shop.getCategory();
    }

    // Abstract steps that MUST be implemented by specific report types
    protected abstract String formatReport(String rawData);
    protected abstract void exportReport(String content);
}
// End Week 6, Pattern Template 1