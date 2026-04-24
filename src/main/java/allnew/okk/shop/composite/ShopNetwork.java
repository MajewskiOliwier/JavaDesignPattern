package allnew.okk.shop.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import allnew.okk.shop.iterator.ShopNetworkIterator;
import allnew.okk.shop.visitor.ShopVisitor;
import lombok.Getter;

// Week 3, Pattern Composite 3
// A class representing a Composite (Node) - a chain of stores or a franchise.
// It can contain individual stores (BaseShop) or other networks (ShopNetwork).
public class ShopNetwork implements ShopComponent, Iterable<ShopComponent> {

    private static final String NETWORK_PREFIX = "Sieć: ";
    private static final String CHILD_PREFIX = "  - ";
    private static final String NEW_LINE = "\n";
    private static final String CHILD_INDENT = "\n  ";
    private static final int EMPTY_COUNT = 0;

    private final String networkName;
    // Week 5, Pattern Iterator 2 (Added Iterable interface)
    @Getter
    private final List<ShopComponent> children = new ArrayList<>();

    public ShopNetwork(String networkName) {
        if (networkName == null || networkName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa sieci nie może być pusta.");
        }
        this.networkName = networkName;
    }

    public void addShopComponent(ShopComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("Komponent sklepu nie może być pusty.");
        }
        children.add(component);
    }

    public void removeShopComponent(ShopComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("Komponent sklepu nie może być pusty.");
        }
        if (!children.contains(component)) {
            throw new IllegalStateException("Nie można usunąć komponentu, który nie należy do tej sieci.");
        }
        children.remove(component);
    }

    @Override
    public String getDetails() {
        StringBuilder builder = new StringBuilder();
        appendNetworkHeader(builder);
        appendChildrenDetails(builder);
        return builder.toString().trim();
    }

    private void appendNetworkHeader(StringBuilder builder) {
        builder.append(NETWORK_PREFIX).append(networkName).append(NEW_LINE);
    }

    private void appendChildrenDetails(StringBuilder builder) {
        for (ShopComponent child : children) {
            String formattedChild = child.getDetails().replace(NEW_LINE, CHILD_INDENT);
            builder.append(CHILD_PREFIX).append(formattedChild).append(NEW_LINE);
        }
    }

    @Override
    public int getShopCount() {
        int totalCount = EMPTY_COUNT;
        for (ShopComponent child : children) {
            totalCount += child.getShopCount();
        }
        return totalCount;
    }

    @Override
    public Iterator<ShopComponent> iterator() {
        return new ShopNetworkIterator(this);
    }
    // End Week 5, Patter Iterator 2

    @Override
    public void accept(ShopVisitor visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException("Wizytator nie może być pusty.");
        }
        for (ShopComponent child : children) {
            child.accept(visitor);
        }
    }
}
// End Week 3, Pattern Composite 3