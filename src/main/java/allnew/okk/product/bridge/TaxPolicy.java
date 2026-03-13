package allnew.okk.product.bridge;

import java.util.List;

// Week 3 , Pattern Bridge
public interface TaxPolicy {
    double calculateTax(double price);
    double calculateTotalPrice(double price);
    double getTaxRate();
    String getRegionCode();
}
// End Week 3 , Pattern Bridge
