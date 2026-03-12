package allnew.okk.product.bridge;

import java.util.List;

// Tydzień 3 , Wzorzec Bridge
public interface TaxPolicy {
    double calculateTax(double price);
    double calculateTotalPrice(double price);
    double getTaxRate();
    String getRegionCode();
}
// Koniec Tydzień 3 , Wzorzec Bridge
