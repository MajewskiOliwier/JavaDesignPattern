package allnew.okk.shop.bridge;

// Week 3, Bridge Pattern 2
// Concrete implementation - sending messages via email.
public class EmailNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(String shopName, String message) {
        System.out.println("[EMAIL] Shop " + shopName + " is sending a message: " + message);
    }
}
// End of Week 3, Bridge Pattern 2