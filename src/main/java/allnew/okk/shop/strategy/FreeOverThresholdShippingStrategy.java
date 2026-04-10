package allnew.okk.shop.strategy;

// Week 6, Pattern Strategy 4
// A concrete strategy: Shipping is free if the order total exceeds a certain amount.
// Otherwise, a standard fee applies.
public class FreeOverThresholdShippingStrategy implements ShippingCostStrategy {
    private final double threshold;
    private final double standardFee;

    public FreeOverThresholdShippingStrategy(double threshold, double standardFee) {
        this.threshold = threshold;
        this.standardFee = standardFee;
    }

    @Override
    public double calculateShippingCost(double orderTotal, double distanceInKm) {
        if (orderTotal >= threshold) {
            System.out.println("Order exceeds threshold. Free shipping applied!");
            return 0.0;
        }
        return standardFee;
    }
}
// End Week 6, Pattern Strategy 4