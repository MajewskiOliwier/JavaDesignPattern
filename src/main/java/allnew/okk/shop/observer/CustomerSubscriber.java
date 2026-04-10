package allnew.okk.shop.observer;

// Week 6, Pattern Observer 2
// A concrete observer representing a customer who subscribed to a specific shop's updates.
public class CustomerSubscriber implements ShopObserver {
    private final String customerEmail;

    public CustomerSubscriber(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public void update(String shopName, String promoMessage) {
        // Here, in a real app, we would send an actual email or push notification to the user
        System.out.println("[SUBSCRIBER " + customerEmail + "] Received alert from " + shopName + ": " + promoMessage);
    }
}
// End Week 6, Pattern Observer 2