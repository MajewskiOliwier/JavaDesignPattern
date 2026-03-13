package allnew.okk.shop.composite;

// Week 3, Composite Pattern 1
// Common interface for individual shops and entire shop networks.
// Allows the client to treat them in a single, uniform way.
public interface ShopComponent {
    String getDetails();
    int getShopCount();
}
// End of Week 3, Composite Pattern 1