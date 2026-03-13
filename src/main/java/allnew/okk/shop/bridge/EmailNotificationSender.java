package allnew.okk.shop.bridge;

// Tydzień 3, Wzorzec Bridge 2
// Konkretna implementacja - wysyłanie wiadomości mailowo.
public class EmailNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(String shopName, String message) {
        System.out.println("[EMAIL] Sklep " + shopName + " wysyła wiadomość: " + message);
    }
}
// Koniec, Tydzień 3, Wzorzec Bridge 2