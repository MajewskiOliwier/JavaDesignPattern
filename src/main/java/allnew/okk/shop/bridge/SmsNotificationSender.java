package allnew.okk.shop.bridge;

// Week 8 - Dependency Inversion Principle 4 (Klasa pochodna / implementacyjna)
// Week 7 - Single Responsibility Principle (SRP)
// Week 3, Pattern Bridge 3
// Konkretna implementacja - wysyłanie wiadomości przez SMS.
public class SmsNotificationSender extends AbstractNotificationSender {

    private static final String NOTIFICATION_TYPE = "SMS";

    @Override
    public void sendNotification(String shopName, String message) {
        processAndSendMessage(NOTIFICATION_TYPE, shopName, message);
    }
}
// End Week 3, Pattern Bridge 3