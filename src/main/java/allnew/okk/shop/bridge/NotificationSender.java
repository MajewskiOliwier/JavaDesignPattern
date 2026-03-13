package allnew.okk.shop.bridge;

// Tydzień 3, Wzorzec Bridge 1
// Interfejs implementacji we wzorcu Bridge.
// Definiuje, w jaki sposób sklep komunikuje się z klientami.
public interface  NotificationSender {
    void sendNotification(String shopName, String message);
}
// Koniec, Tydzień 3, Wzorzec Bridge 1