package allnew.okk.shop.strategy;

// Week 6, Pattern Strategy 2
// A concrete strategy: Always charges a fixed rate regardless of distance or order size.
public class FlatRateShippingStrategy implements ShippingCostStrategy {
    private final double flatRate;

    public FlatRateShippingStrategy(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calculateShippingCost(double orderTotal, double distanceInKm) {
        return flatRate;
    }
}
// End Week 6, Pattern Strategy 2