package allnew.okk.shop.isp;

// Week 8 - Interface Segregation Principle 1 (Gruby interfejs - Antywzorzec)
// Gruby interfejs zmuszający każdego sprzedawcę do implementacji wielu metod
public interface MarketplaceShopAPI {
    // Zarządzanie ofertą
    void listProduct(String productName, double price);
    void updateProductStock(String productId, int quantity);

    // Obsługa zamówień
    void processOrder(String orderId);
    void cancelOrder(String orderId, String reason);

    // Funkcje Marketingowe
    void launchAdCampaign(String adType);
    void createSuperPromoDiscount(int percentage);

    // Analityka
    void generateSalesAnalyticsReport(String dateFrom, String dateTo);

    // Obsługa klienta
    void respondToCustomerQuestion(String questionId, String answer);
    void openDisputeWithBuyer(String orderId, String reason);

    // Funkcje B2B i zaawansowane
    void issueB2bInvoice(String orderId, String taxId);
    void setupWarehouseAPIIntegration(String apiKey);
}