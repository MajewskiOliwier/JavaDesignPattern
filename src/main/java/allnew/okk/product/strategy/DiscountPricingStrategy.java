package allnew.okk.product.strategy;

// Week 6, pattern strategy Marciniuk
public class DiscountPricingStrategy implements ProductPricingStrategy {
    private double discountPercentage;

    public DiscountPricingStrategy(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateFinalPrice(double basePrice) {
        return basePrice * (1 - discountPercentage / 100);
    }
}
// End Week 6, pattern strategy Marciniuk