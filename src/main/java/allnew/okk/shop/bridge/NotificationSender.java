package allnew.okk.shop.bridge;

// Week 3, Pattern Bridge 1
// Interfejs implementacji we wzorcu Bridge.
// Definiuje, w jaki sposób sklep komunikuje się z klientami.
public interface  NotificationSender {
    void sendNotification(String shopName, String message);
}
// End Week 3, Pattern Bridge 1