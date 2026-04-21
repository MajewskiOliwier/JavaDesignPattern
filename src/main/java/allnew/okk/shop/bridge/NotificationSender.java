package allnew.okk.shop.bridge;

// Week 8 - Dependency Inversion Principle 1 (Interfejs)
// Jest to abstrakcja najwyższego poziomu. Moduły wysokopoziomowe (jak BaseShop)
// zależą wyłącznie od tego interfejsu. "Nie wiedzą", jak fizycznie wysłać wiadomość,
// wiedzą tylko, że każdy system powiadomień musi posiadać taką metodę.

// Week 3, Pattern Bridge 1
// Interfejs implementacji we wzorcu Bridge.
// Definiuje, w jaki sposób sklep komunikuje się z klientami.
public interface NotificationSender {
    void sendNotification(String shopName, String message);
}
// End Week 3, Pattern Bridge 1