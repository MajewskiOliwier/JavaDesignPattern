package allnew.okk.shop.composite;

// Tydzień 3, Wzorzec Composite 1
// Wspólny interfejs dla pojedynczych sklepów i całych sieci sklepów.
// Pozwala klientowi traktować je w ten sam, jednolity sposób.
public interface ShopComponent {
    String getDetails();
    int getShopCount();
}
// Koniec, Tydzień 3, Wzorzec Composite 1