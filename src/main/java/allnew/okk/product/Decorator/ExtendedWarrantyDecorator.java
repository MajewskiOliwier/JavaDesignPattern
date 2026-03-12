package allnew.okk.product.Decorator;

import allnew.okk.basket.composite.PurchasableItem;

// Tydzień 3, Wzorzec Dekorator 2
// Dekorator służący do dodania opcji przedłużonej gwarancji do produktu.
public class ExtendedWarrantyDecorator extends ProductDecorator {
    private final int warrantyMonths = 12;
    private final double warrantyCost = 100.0;

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
}
// Koniec Tydzień 3, Wzorzec Dekorator 2
