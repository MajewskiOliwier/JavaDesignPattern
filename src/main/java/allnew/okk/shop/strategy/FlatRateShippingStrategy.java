package allnew.okk.shop.strategy;
// Week 9 - Maintaining Clean Code Principles
// Week 6, Pattern Strategy 2
// A concrete strategy: Always charges a fixed rate regardless of distance or order size.
public class FlatRateShippingStrategy implements ShippingCostStrategy {

    private final double flatRate;

    public FlatRateShippingStrategy(double flatRate) {
        if (flatRate < 0) {
            throw new IllegalArgumentException("Zryczałtowana opłata nie może być ujemna.");
        }
        this.flatRate = flatRate;
    }

    @Override
    public double calculateShippingCost(double orderTotal, double distanceInKm) {
        validateInputs(orderTotal, distanceInKm);
        return flatRate;
    }

    private void validateInputs(double orderTotal, double distanceInKm) {
        if (orderTotal < 0 || distanceInKm < 0) {
            throw new IllegalArgumentException("Wartość zamówienia i dystans nie mogą być ujemne.");
        }
    }
}
// End Week 6, Pattern Strategy 2