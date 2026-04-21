package allnew.okk.shop.strategy;
// Week 9 - Maintaining Clean Code Principles
// Week 6, Pattern Strategy 3
// A concrete strategy: Calculates cost based on the distance to the customer.
public class DistanceBasedShippingStrategy implements ShippingCostStrategy {

    private final double ratePerKm;

    public DistanceBasedShippingStrategy(double ratePerKm) {
        if (ratePerKm < 0) {
            throw new IllegalArgumentException("Stawka za kilometr nie może być ujemna.");
        }
        this.ratePerKm = ratePerKm;
    }

    @Override
    public double calculateShippingCost(double orderTotal, double distanceInKm) {
        validateInputs(orderTotal, distanceInKm);
        return distanceInKm * ratePerKm;
    }

    private void validateInputs(double orderTotal, double distanceInKm) {
        if (orderTotal < 0 || distanceInKm < 0) {
            throw new IllegalArgumentException("Wartość zamówienia i dystans nie mogą być ujemne.");
        }
    }
}
// End Week 6, Pattern Strategy 3