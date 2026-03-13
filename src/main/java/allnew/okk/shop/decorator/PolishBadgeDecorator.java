package allnew.okk.shop.decorator;

// Week 3, Decorator Pattern 6
// "Polish Company" Decorator
// Adds a flag before the shop name
public class PolishBadgeDecorator extends ShopDecorator {

    public PolishBadgeDecorator(ShopDisplay wrappedShop) {
        super(wrappedShop);
    }

    @Override
    public String getDisplayName() {
        return "🇵🇱 " + super.getDisplayName();
    }

    @Override
    public String getDisplayDescription() {
        return super.getDisplayDescription() + " (By buying here, you support Polish capital).";
    }
}
// End of Week 3, Decorator Pattern 6