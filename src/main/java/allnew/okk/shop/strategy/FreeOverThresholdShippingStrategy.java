package allnew.okk.shop.strategy;
// Week 9 - Maintaining Clean Code Principles
// Week 6, Pattern Strategy 4
// A concrete strategy: Shipping is free if the order total exceeds a certain amount.
// Otherwise, a standard fee applies.
public class FreeOverThresholdShippingStrategy implements ShippingCostStrategy {

    private static final double FREE_SHIPPING_COST = 0.0;
    private static final String LOG_FREE_SHIPPING = "[DOSTAWA] Przekroczono próg zamówienia. Zastosowano darmową dostawę!";

    private final double threshold;
    private final double standardFee;

    public FreeOverThresholdShippingStrategy(double threshold, double standardFee) {
        if (threshold < 0 || standardFee < 0) {
            throw new IllegalArgumentException("Próg darmowej dostawy i opłata standardowa nie mogą być ujemne.");
        }
        this.threshold = threshold;
        this.standardFee = standardFee;
    }

    @Override
    public double calculateShippingCost(double orderTotal, double distanceInKm) {
        validateInputs(orderTotal, distanceInKm);

        if (isEligibleForFreeShipping(orderTotal)) {
            logFreeShipping();
            return FREE_SHIPPING_COST;
        }

        return standardFee;
    }

    private void validateInputs(double orderTotal, double distanceInKm) {
        if (orderTotal < 0 || distanceInKm < 0) {
            throw new IllegalArgumentException("Wartość zamówienia i dystans nie mogą być ujemne.");
        }
    }

    private boolean isEligibleForFreeShipping(double orderTotal) {
        return orderTotal >= threshold;
    }

    private void logFreeShipping() {
        System.out.println(LOG_FREE_SHIPPING);
    }
}
// End Week 6, Pattern Strategy 4