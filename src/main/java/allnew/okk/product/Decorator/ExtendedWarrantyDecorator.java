package allnew.okk.product.Decorator;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.Visitor.BasketVisitor;
import allnew.okk.basket.composite.PurchasableItem;

// Week 3, Pattern Decorator 2, jakub marciniuk
// Dekorator służący do dodania opcji przedłużonej gwarancji do produktu.
public class ExtendedWarrantyDecorator extends ProductDecorator {
    private final int warrantyMonths = 12;
    private double warrantyCost = 100.0;

    // Konstruktor przyjmujący produkt do dekoracji, liczbę miesięcy gwarancji oraz koszt gwarancji.
    public ExtendedWarrantyDecorator(PurchasableItem wrappedItem) {
        super(wrappedItem);
    }

    public double getWarrantyCost() {
        return warrantyCost;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public void setPrice(double newPrice) {
        warrantyCost = newPrice;
    }

    // Nadpisanie metody getPrice, aby uwzględnić koszt przedłużonej gwarancji.
    @Override
    public double getPrice() {
        return wrappedItem.getPrice() + warrantyCost;
    }

    // Nadpisanie nazwy produktu, aby uwzględnić dodaną opcję przedłużonej gwarancji.
    @Override
    public String getItemName() {
        return wrappedItem.getItemName() + " + Extended Warranty (" + warrantyMonths + " months)";
    }

    @Override
    public String getSellerName() {
        return wrappedItem.getSellerName();
    }

    @Override
    public String getSellerID() {
        return wrappedItem.getSellerID();
    }

    //Week 6, Pattern Visitor Oliwier Majewski
    @Override
    public void accept(BasketVisitor visitor) {
        wrappedItem.accept(visitor);
    }
    //End Week 6, Pattern Visitor Oliwier Majewski
}
// End Week 3, Pattern Decorator 2, jakub marciniuk
