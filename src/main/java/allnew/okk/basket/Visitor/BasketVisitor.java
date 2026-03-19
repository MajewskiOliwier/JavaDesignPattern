package allnew.okk.basket.Visitor;

import allnew.okk.basket.composite.SellerBasket;
import allnew.okk.basket.composite.ShoppingBasket;
import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;

// Week 6, Pattern Visitor
//This interface defines method that the Visitors that use Basket require
public interface BasketVisitor {

    //Those 2 methods are not implementing anything in the TaxCalculatorVisitor but are required in case we need to create a new Basket visitor for example DiscountVisitor
    default void visit(ShoppingBasket basket) {};
    default void visit(SellerBasket sellerBasket) {};

    void visit(CompanyProduct product);
    void visit(PrivateProduct product);
}
//End Week 6, Pattern Visitor