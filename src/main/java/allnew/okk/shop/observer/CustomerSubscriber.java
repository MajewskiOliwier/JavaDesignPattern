package allnew.okk.shop.observer;
// Week 9 - Maintaining Clean Code Principles
// Week 6, Pattern Observer 2
// A concrete observer representing a customer who subscribed to a specific shop's updates.
public class CustomerSubscriber implements ShopObserver {

    private static final String LOG_TEMPLATE = "[SUBSKRYBENT %s] Otrzymano powiadomienie od %s: %s";

    private final String customerEmail;

    public CustomerSubscriber(String customerEmail) {
        if (customerEmail == null || customerEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Adres email subskrybenta nie może być pusty.");
        }
        this.customerEmail = customerEmail;
    }

    @Override
    public void update(String shopName, String promoMessage) {
        validateUpdateInputs(shopName, promoMessage);
        logNotification(shopName, promoMessage);
    }

    private void validateUpdateInputs(String shopName, String promoMessage) {
        if (shopName == null || shopName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa sklepu nie może być pusta.");
        }
        if (promoMessage == null || promoMessage.trim().isEmpty()) {
            throw new IllegalArgumentException("Wiadomość promocyjna nie może być pusta.");
        }
    }

    private void logNotification(String shopName, String promoMessage) {
        String logMessage = String.format(LOG_TEMPLATE, customerEmail, shopName, promoMessage);
        System.out.println(logMessage);
    }
}
// End Week 6, Pattern Observer 2