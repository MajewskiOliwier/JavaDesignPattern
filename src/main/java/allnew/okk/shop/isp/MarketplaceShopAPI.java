package allnew.okk.shop.isp;

// Week 8 - Interface Segregation Principle 1 (Fat Interface - Antipattern)
// A fat interface that forces every vendor to implement multiple methods
public interface MarketplaceShopAPI {
    // Offer management
     void listProduct(String productName, double price);
    void updateProductStock(String productId, int quantity);

    // Order processing
    void processOrder(String orderId);
    void cancelOrder(String orderId, String reason);

    // Marketing Features
    void launchAdCampaign(String adType);
    void createSuperPromoDiscount(int percentage);

    // Analytics
    void generateSalesAnalyticsReport(String dateFrom, String dateTo);

    // Customer service
    void respondToCustomerQuestion(String questionId, String answer);
    void openDisputeWithBuyer(String orderId, String reason);

    // B2B and advanced methods
    void issueB2bInvoice(String orderId, String taxId);
    void setupWarehouseAPIIntegration(String apiKey);
}