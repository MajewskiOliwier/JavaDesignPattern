package allnew.okk.shop.bridge;

// Tydzień 3, Wzorzec Bridge 3
// Konkretna implementacja - wysyłanie wiadomości przez SMS.
public class SmsNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(String shopName, String message) {
        System.out.println("[SMS] Sklep " + shopName + " wysyła wiadomość: " + message);
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 3