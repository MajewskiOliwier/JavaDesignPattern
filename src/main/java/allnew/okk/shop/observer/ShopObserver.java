package allnew.okk.shop.observer;

// Week 6, Pattern Observer 1
// The Observer interface. Any class that wants to listen to shop updates
// must implement this interface in order to receive notifications.
public interface ShopObserver {
    void update(String shopName, String promoMessage);
}
// End Week 6, Pattern Observer 1