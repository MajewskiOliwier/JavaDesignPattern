package allnew.okk.shop.bridge;

// Week 3, Bridge Pattern 3
// Concrete implementation - sending messages via SMS.
public class SmsNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(String shopName, String message) {
        System.out.println("[SMS] Shop " + shopName + " is sending a message: " + message);
    }
}
// End of Week 3, Bridge Pattern 3