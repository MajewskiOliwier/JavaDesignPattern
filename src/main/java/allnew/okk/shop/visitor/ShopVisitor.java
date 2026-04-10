package allnew.okk.shop.visitor;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;

// Week 6, Pattern Visitor 1
// Interface defining the Visitor. It declares a visit method for each
// specific concrete class in the object structure.
public interface ShopVisitor {
    void visit(PhysicalShop physicalShop);
    void visit(OnlineShop onlineShop);
}
// End Week 6, Pattern Visitor 1