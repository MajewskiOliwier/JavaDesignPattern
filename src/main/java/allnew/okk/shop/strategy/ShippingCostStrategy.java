package allnew.okk.shop.strategy;
// Week 9 - Maintaining Clean Code Principles
// Week 6, Pattern Strategy 1
// Interface defining the strategy for calculating shipping costs.
// Different shops can use different algorithms without changing the shop class itself.
public interface ShippingCostStrategy {
    double calculateShippingCost(double orderTotal, double distanceInKm);
}
// End Week 6, Pattern Strategy 1