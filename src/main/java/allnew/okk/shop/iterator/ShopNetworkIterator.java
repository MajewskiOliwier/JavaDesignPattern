package allnew.okk.shop.iterator;

import allnew.okk.shop.composite.ShopComponent;
import allnew.okk.shop.composite.ShopNetwork;
import allnew.okk.shop.model.BaseShop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// Week 9 - Maintaining Clean Code Principles

// Week 7 - Single Responsibility Principle (SRP)
// Week 5, Pattern Iterator 1
// Iterator that traverses the composite structure of ShopNetwork.
// It recursively flattens the tree and returns only individual shops (leaves).
public class ShopNetworkIterator implements Iterator<ShopComponent> {

    private static final int INITIAL_INDEX = 0;

    private final List<ShopComponent> flatShopList = new ArrayList<>();
    private int currentIndex = INITIAL_INDEX;

    public ShopNetworkIterator(ShopComponent rootNode) {
        if (rootNode == null) {
            throw new IllegalArgumentException("Węzeł główny struktury nie może być pusty.");
        }
        extractLeaves(rootNode);
    }

    private void extractLeaves(ShopComponent node) {
        if (node instanceof ShopNetwork networkNode) {
            processNetworkNode(networkNode);
        } else if (node instanceof BaseShop leafNode) {
            processLeafNode(leafNode);
        }
    }

    private void processNetworkNode(ShopNetwork networkNode) {
        for (ShopComponent child : networkNode.getChildren()) {
            extractLeaves(child);
        }
    }

    private void processLeafNode(BaseShop leafNode) {
        flatShopList.add(leafNode);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < flatShopList.size();
    }

    @Override
    public ShopComponent next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Brak kolejnych sklepów do iteracji.");
        }
        return flatShopList.get(currentIndex++);
    }
}
// End Week 5, Pattern Iterator 1