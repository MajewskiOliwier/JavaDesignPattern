package allnew.okk.shop.strategy;

// Week 6, Pattern Strategy 3
// A concrete strategy: Calculates cost based on the distance to the customer.
public class DistanceBasedShippingStrategy implements ShippingCostStrategy {
    private final double ratePerKm;

    public DistanceBasedShippingStrategy(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    @Override
    public double calculateShippingCost(double orderTotal, double distanceInKm) {
        return distanceInKm * ratePerKm;
    }
}
// End Week 6, Pattern Strategy 3