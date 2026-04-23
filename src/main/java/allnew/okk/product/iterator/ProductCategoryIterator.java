package allnew.okk.product.iterator;

import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.product.command.ProductCommand;
import allnew.okk.product.composite.ProductCategoryNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// Week 4, Pattern Iterator Jakub Marciniuk
public class ProductCategoryIterator implements Iterator<PurchasableItem> {
    private final List<PurchasableItem> items = new ArrayList<>();
    private int currentIndex = 0;

    public ProductCategoryIterator(ProductCategoryNode node){
        traverse(node);
    }

    private void traverse(ProductCategoryNode node) {
        if(node instanceof ProductCategoryNode categoryNode){
            for(PurchasableItem item: categoryNode.getChildren()){
                traverse((ProductCategoryNode) item);
            }
        }else{
            items.add(node);
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < items.size();
    }

    @Override
    public PurchasableItem next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more items to iterate.");
        }
        return items.get(currentIndex++);
    }
}
// end of week 4, pattern iterator, jakub marciniuk
