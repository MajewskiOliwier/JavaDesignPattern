package allnew.okk.shop.bridge;

// Week 3, Bridge Pattern 1
// Implementation interface in the Bridge pattern.
// Defines how the shop communicates with customers.
public interface NotificationSender {
    void sendNotification(String shopName, String message);
}
// End of Week 3, Bridge Pattern 1