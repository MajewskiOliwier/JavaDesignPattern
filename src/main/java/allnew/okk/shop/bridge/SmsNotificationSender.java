package allnew.okk.shop.bridge;

// Week 8 - Dependency Inversion Principle 4 (Klasa pochodna / implementacyjna)
// Week 7 - Single Responsibility Principle (SRP)
// Week 3, Pattern Bridge 3
// Konkretna implementacja - wysyłanie wiadomości przez SMS.
public class SmsNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(String shopName, String message) {
        System.out.println("[SMS] Sklep " + shopName + " wysyła wiadomość: " + message);
    }
}
// End Week 3, Pattern Bridge 3