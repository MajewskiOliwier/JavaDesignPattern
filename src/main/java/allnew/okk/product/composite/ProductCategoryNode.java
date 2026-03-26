package allnew.okk.product.composite;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.Visitor.BasketVisitor;
import allnew.okk.basket.composite.PurchasableItem;
import allnew.okk.product.iterator.ProductCategoryIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Week 3, Pattern Composite Jakub Marciniuk
public class ProductCategoryNode implements PurchasableItem {
    // Nazwa kategorii, np. "Elektronika", "Odzież", itp.
    private String categoryName;
    // Lista dzieci może zawierać zarówno inne kategorie (ProductCategoryNode), jak i produkty liściowe (LeafProduct)
    private List<PurchasableItem> children = new ArrayList<>();
    public ProductCategoryNode(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addChild(PurchasableItem item) {
        children.add(item);
    }

    public void removeChild(PurchasableItem item) {
        children.remove(item);
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getItemName() {
        return categoryName;
    }

    @Override
    public String getSellerName() {
        return "";
    }

    @Override
    public String getSellerID() {
        return "";
    }

    @Override
    public AccountDisplayable getSellerAccount() {
        return null;
    }

    @Override
    public List<PurchasableItem> getChildren() {
        return children;
    }

    //Week 6, Pattern Visitor Oliwier Majewski
    @Override
    public void accept(BasketVisitor visitor) {}
    //End Week 6, Pattern Visitor Oliwier Majewski

    public List<PurchasableItem> getAllProducts() {
        List<PurchasableItem> allProducts = new ArrayList<>(children);
        for (PurchasableItem child : children) {
            if(child instanceof ProductCategoryNode) {
                allProducts.addAll(((ProductCategoryNode) child).getAllProducts());
            }
        }
        return allProducts;
    }

    // Metoda pomocnicza do pobierania wszystkich produktów liściowych w hierarchii
    public List<PurchasableItem> getAllLeafProducts() {
        List<PurchasableItem> leaves = new ArrayList<>(children);
        for (PurchasableItem child : children) {
            if(child instanceof ProductCategoryNode) {
                leaves.addAll(child.getChildren());
            }
            else if(child.getChildren() == null || child.getChildren().isEmpty()){
                leaves.add(child);
            }
        }
        return leaves;
    }

    // week 5, pattern Iterator 2 Jakub Marciniuk
    public Iterator<PurchasableItem> iterator(){
        return new ProductCategoryIterator(this);
    }
    // end, week 5, pattern Iterator 2

}
// End Week 3, Pattern Composite