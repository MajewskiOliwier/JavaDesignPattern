package allnew.okk.shop.composite;

import allnew.okk.shop.visitor.ShopVisitor;

// Week 3, Pattern Composite 1
// A common interface for individual stores and entire chains of stores.
// Allows customers to treat them in the same, uniform manner.
public interface ShopComponent {
    String getDetails();
    int getShopCount();

    // Week 6, Pattern Visitor 3
    void accept(ShopVisitor visitor);
}
// End Week 3, Pattern Composite 1