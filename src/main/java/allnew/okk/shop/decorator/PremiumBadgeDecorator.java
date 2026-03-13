package allnew.okk.shop.decorator;

// Week 3, Decorator Pattern 4
// "Premium" Decorator.
// Adds a star icon before the shop name.
public class PremiumBadgeDecorator extends ShopDecorator {

    public PremiumBadgeDecorator(ShopDisplay wrappedShop) {
        super(wrappedShop);
    }

    @Override
    public String getDisplayName() {
        return "⭐ " + super.getDisplayName();
    }

    @Override
    public String getDisplayDescription() {
        return super.getDisplayDescription() + " (PREMIUM rank shop).";
    }
}
// End of Week 3, Decorator Pattern 4