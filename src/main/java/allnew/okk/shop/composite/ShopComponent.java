package allnew.okk.shop.composite;

// Week 3, Pattern Composite 1
// Wspólny interfejs dla pojedynczych sklepów i całych sieci sklepów.
// Pozwala klientowi traktować je w ten sam, jednolity sposób.
public interface ShopComponent {
    String getDetails();
    int getShopCount();
}
// End Week 3, Pattern Composite 1