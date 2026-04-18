package allnew.okk.shop.bridge;

// Week 7 - Single Responsibility Principle (SRP)
// Week 3, Pattern Bridge 2
// Konkretna implementacja - wysyłanie wiadomości mailowo.
public class EmailNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(String shopName, String message) {
        System.out.println("[EMAIL] Sklep " + shopName + " wysyła wiadomość: " + message);
    }
}
// End Week 3, Pattern Bridge 2