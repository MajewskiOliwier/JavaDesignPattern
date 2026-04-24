package allnew.okk.shop.bridge;

// Week 8 - Dependency Inversion Principle 3 (Derived/Implementation Class)
// Week 7 - Single Responsibility Principle (SRP)
// Week 3, Pattern Bridge 2
// Concrete implementation - sending messages via email.
public class EmailNotificationSender extends AbstractNotificationSender {

    private static final String NOTIFICATION_TYPE = "EMAIL";

    @Override
    public void sendNotification(String shopName, String message) {
        processAndSendMessage(NOTIFICATION_TYPE, shopName, message);
    }
}
// End Week 3, Pattern Bridge 2