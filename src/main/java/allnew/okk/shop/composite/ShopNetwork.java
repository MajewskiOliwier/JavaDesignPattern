package allnew.okk.shop.composite;

import java.util.ArrayList;
import java.util.List;

// Week 3, Composite Pattern 3
// Class representing the Composite (Node) - a shop network or franchise.
// It can contain individual shops (BaseShop) or other networks (ShopNetwork).
public class ShopNetwork implements ShopComponent {

    private String networkName;

    // List storing child elements (shops or inherited shop networks)
    private List<ShopComponent> children = new ArrayList<>();

    public ShopNetwork(String networkName) {
        this.networkName = networkName;
    }

    // Methods for managing children
    public void addShopComponent(ShopComponent component) {
        children.add(component);
    }

    public void removeShopComponent(ShopComponent component) {
        children.remove(component);
    }

    // Delegating calls to all children and aggregating the results
    @Override
    public String getDetails() {
        StringBuilder builder = new StringBuilder();
        builder.append("Network: ").append(networkName).append("\n");
        for (ShopComponent child : children) {
            builder.append("  - ").append(child.getDetails().replace("\n", "\n  ")).append("\n");
        }
        return builder.toString().trim();
    }

    @Override
    public int getShopCount() {
        int totalCount = 0;
        for (ShopComponent child : children) {
            // Recursive call to count down the tree structure
            totalCount += child.getShopCount();
        }
        return totalCount;
    }
}
// End of Week 3, Composite Pattern 3