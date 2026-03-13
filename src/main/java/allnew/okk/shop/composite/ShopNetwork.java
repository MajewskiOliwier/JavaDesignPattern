package allnew.okk.shop.composite;

import java.util.ArrayList;
import java.util.List;

// Tydzień 3, Wzorzec Composite 3
// Klasa reprezentująca Kompozyt (Węzeł) - sieć sklepów lub franczyzę.
// Może zawierać w sobie pojedyncze sklepy (BaseShop) lub inne sieci (ShopNetwork).
public class ShopNetwork implements ShopComponent {

    private String networkName;

    // Lista przechowująca elementy podrzędne (sklepy lub dziedziczące sieci sklepów)
    private List<ShopComponent> children = new ArrayList<>();

    public ShopNetwork(String networkName) {
        this.networkName = networkName;
    }

    // Metody do zarządzania dziećmi
    public void addShopComponent(ShopComponent component) {
        children.add(component);
    }

    public void removeShopComponent(ShopComponent component) {
        children.remove(component);
    }

    // Delegacja wywołań do wszystkich dzieci i agregacja wyników
    @Override
    public String getDetails() {
        StringBuilder builder = new StringBuilder();
        builder.append("Sieć: ").append(networkName).append("\n");
        for (ShopComponent child : children) {
            builder.append("  - ").append(child.getDetails().replace("\n", "\n  ")).append("\n");
        }
        return builder.toString().trim();
    }

    @Override
    public int getShopCount() {
        int totalCount = 0;
        for (ShopComponent child : children) {
            // Rekurencyjne wywołanie zliczania w dół struktury drzewiastej
            totalCount += child.getShopCount();
        }
        return totalCount;
    }
}
// Koniec, Tydzień 3, Wzorzec Composite 3