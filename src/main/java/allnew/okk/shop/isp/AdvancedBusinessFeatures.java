package allnew.okk.shop.isp;

// Week 8 - Interface Segregation Principle 6 (Wydzielony interfejs 5)
public interface AdvancedBusinessFeatures {
    void generateSalesAnalyticsReport(String dateFrom, String dateTo);
    void issueB2bInvoice(String orderId, String taxId);
    void setupWarehouseAPIIntegration(String apiKey);
}