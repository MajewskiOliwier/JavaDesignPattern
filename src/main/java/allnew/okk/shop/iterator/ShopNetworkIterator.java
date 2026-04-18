package allnew.okk.shop.iterator;

import allnew.okk.shop.composite.ShopComponent;
import allnew.okk.shop.composite.ShopNetwork;
import allnew.okk.shop.model.BaseShop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// Week 7 - Single Responsibility Principle (SRP)
// Week 5, Pattern Iterator 1
// Iterator that traverses the composite structure of ShopNetwork.
// It recursively flattens the tree and returns only individual shops (leaves).
public class ShopNetworkIterator implements Iterator<ShopComponent> {

    // List to hold the flattened structure
    private final List<ShopComponent> flatShopList = new ArrayList<>();
    private int currentIndex = 0;

    public ShopNetworkIterator(ShopComponent rootNode) {
        traverse(rootNode);
    }

    // Recursively visits nodes to extract leaf elements (BaseShop)
    private void traverse(ShopComponent node) {
        if (node instanceof ShopNetwork networkNode) {
            // If it's a network, go deeper into its children
            for (ShopComponent child : networkNode.getChildren()) {
                traverse(child);
            }
        } else if (node instanceof BaseShop) {
            // If it's a single shop (leaf), add it to our flat list
            flatShopList.add(node);
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < flatShopList.size();
    }

    @Override
    public ShopComponent next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more shops to iterate.");
        }
        return flatShopList.get(currentIndex++);
    }
}
// End Week 5, Pattern Iterator 1