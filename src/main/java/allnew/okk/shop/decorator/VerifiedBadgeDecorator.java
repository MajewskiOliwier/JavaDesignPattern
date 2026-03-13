package allnew.okk.shop.decorator;

// Week 3, Decorator Pattern 5
// "Verified" Decorator
// Adds an icon before the shop name
public class VerifiedBadgeDecorator extends ShopDecorator {

    public VerifiedBadgeDecorator(ShopDisplay wrappedShop) {
        super(wrappedShop);
    }

    @Override
    public String getDisplayName() {
        return "✔️ " + super.getDisplayName();
    }

    @Override
    public String getDisplayDescription() {
        return super.getDisplayDescription() + " (VERIFIED shop).";
    }
}
// End of Week 3, Decorator Pattern 5