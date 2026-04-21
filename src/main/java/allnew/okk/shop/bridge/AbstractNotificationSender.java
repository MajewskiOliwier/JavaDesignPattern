package allnew.okk.shop.bridge;

// Week 8 - Dependency Inversion Principle 2 (Klasa abstrakcyjna)
// Częściowa implementacja abstrakcji. Zapewnia wspólną logikę dla wszystkich konkretnych typów powiadomień.
public abstract class AbstractNotificationSender implements NotificationSender {

    // Wspólna metoda dla wszystkich klas pochodnych
    protected void logNotificationAttempt(String type, String shopName) {
        System.out.println("[LOG] Inicjowanie systemu " + type + " dla sklepu: " + shopName);
    }

    @Override
    public abstract void sendNotification(String shopName, String message);
}