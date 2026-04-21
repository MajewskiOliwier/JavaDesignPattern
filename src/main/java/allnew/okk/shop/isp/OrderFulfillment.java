package allnew.okk.shop.isp;

// Week 8 - Interface Segregation Principle 3 (Wydzielony interfejs 2)
public interface OrderFulfillment {
    void processOrder(String orderId);
    void cancelOrder(String orderId, String reason);
}