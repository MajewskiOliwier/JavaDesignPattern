package allnew.okk.product.strategy;

// Week 6, pattern strategy Marciniuk
public class StandardPricingStrategy implements ProductPricingStrategy {
    @Override
    public double calculateFinalPrice(double basePrice) {
        return basePrice;
    }
}
// End Week 6, pattern strategy Marciniuk