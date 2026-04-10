package allnew.okk.product.strategy;

// Week 6, pattern strategy Marciniuk
public class BulkPricingStrategy implements ProductPricingStrategy {
    private int minimumQuantity;
    private double discountPercentage;

    public BulkPricingStrategy(int minimumQuantity, double discountPercentage) {
        this.minimumQuantity = minimumQuantity;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateFinalPrice(double basePrice) {
        if (minimumQuantity > 0) {
            return basePrice * (1 - discountPercentage / 100);
        }
        return basePrice;
    }
}
// End Week 6, pattern strategy Marciniuk
