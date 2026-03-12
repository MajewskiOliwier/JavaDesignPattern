package allnew.okk.product.bridge;

import java.util.List;

// Tydzień 3 , Wzorzec Bridge
public class CountryTaxPolicy implements TaxPolicy {
    private String countryCode;
    private double taxRate;

    public CountryTaxPolicy(String countryCode, double taxRate) {
        this.countryCode = countryCode;
        this.taxRate = taxRate;
    }

    @Override
    public double calculateTax(double price) {
        return price * taxRate;
    }

    @Override
    public double calculateTotalPrice(double price) {
        return price + calculateTax(price);
    }

    @Override
    public String getRegionCode() {
        return countryCode;
    }

    @Override
    public double getTaxRate() {
        return taxRate;
    }
}
// Koniec Tydzień 3 , Wzorzec Bridge
