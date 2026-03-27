package allnew.okk.shop.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import allnew.okk.shop.iterator.ShopNetworkIterator;

// Week 3, Pattern Composite 3
// Klasa reprezentująca Kompozyt (Węzeł) - sieć sklepów lub franczyzę.
// Może zawierać w sobie pojedyncze sklepy (BaseShop) lub inne sieci (ShopNetwork).
public class ShopNetwork implements ShopComponent, Iterable<ShopComponent> {
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

    // Week 5, Pattern Iterator 2 (Added Iterable interface)
    public List<ShopComponent> getChildren() {
        return children;
    }

    @Override
    public Iterator<ShopComponent> iterator() {
        return new ShopNetworkIterator(this);
    }

    // End Week 5, Patter Iterator 2
}
// End Week 3, Pattern Composite 3