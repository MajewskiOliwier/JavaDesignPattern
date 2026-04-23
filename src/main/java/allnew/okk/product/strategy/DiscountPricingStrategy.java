package allnew.okk.product.strategy;

// Week 8, Liskov Substitution Principle Marciniuk
// Week 6, pattern strategy Marciniuk
public class DiscountPricingStrategy implements ProductPricingStrategy {
    private final double discountPercentage = 0.20;

    @Override
    public double calculateFinalPrice(double basePrice) {
        return basePrice * (1 - discountPercentage / 100);
    }
}
// End Week 6, pattern strategy Marciniuk
// End Week 8, Liskov Substitution Principle Marciniuk