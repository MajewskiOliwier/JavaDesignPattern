package allnew.okk.shop.composite;

import allnew.okk.shop.visitor.ShopVisitor;

// Week 3, Pattern Composite 1
// Wspólny interfejs dla pojedynczych sklepów i całych sieci sklepów.
// Pozwala klientowi traktować je w ten sam, jednolity sposób.
public interface ShopComponent {
    String getDetails();
    int getShopCount();

    // Week 6, Pattern Visitor 3
    void accept(ShopVisitor visitor);
}
// End Week 3, Pattern Composite 1