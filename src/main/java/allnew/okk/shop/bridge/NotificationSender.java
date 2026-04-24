package allnew.okk.shop.bridge;

// Week 8 - Dependency Inversion Principle 1 (Interface)
// This is the highest-level abstraction. High-level modules (like BaseShop)
// depend solely on this interface. They "don't know" how to physically send a message,
// they only know that every notification system must have such a method.

// Week 3, Pattern Bridge 1
// Implementation interface in the Bridge pattern.
// Defines how the store communicates with customers.
public interface NotificationSender {
    void sendNotification(String shopName, String message);
}
// End Week 3, Pattern Bridge 1