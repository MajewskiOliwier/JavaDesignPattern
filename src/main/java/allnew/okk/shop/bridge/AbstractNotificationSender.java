package allnew.okk.shop.bridge;
// Week 8 - Dependency Inversion Principle 2 (Abstract Class)
// A partial implementation of the abstraction. Provides common logic for all concrete notification types.
 public abstract class AbstractNotificationSender implements NotificationSender {

    private static final String LOG_PREFIX = "[LOG] Inicjowanie systemu ";
    private static final String LOG_SUFFIX = " dla sklepu: ";
    private static final String MESSAGE_TEMPLATE = "[%s] Sklep %s wysyła wiadomość: %s";

    protected void processAndSendMessage(String type, String shopName, String message) {
        validateInputs(shopName, message);
        logNotificationAttempt(type, shopName);
        deliverMessage(type, shopName, message);
    }

    private void validateInputs(String shopName, String message) {
        if (shopName == null || shopName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa sklepu nie może być pusta.");
        }
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Treść wiadomości nie może być pusta.");
        }
    }

    protected void logNotificationAttempt(String type, String shopName) {
        System.out.println(LOG_PREFIX + type + LOG_SUFFIX + shopName);
    }

    private void deliverMessage(String type, String shopName, String message) {
        System.out.println(String.format(MESSAGE_TEMPLATE, type, shopName, message));
    }

    @Override
    public abstract void sendNotification(String shopName, String message);
}