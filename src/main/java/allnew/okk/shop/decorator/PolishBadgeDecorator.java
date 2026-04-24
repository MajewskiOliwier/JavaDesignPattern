package allnew.okk.shop.decorator;

// Week 9 - Maintaining Clean Code Principles

// Week 8 - Liskov Substitution Principle 3 (Derived Class)
// A derived decorator class. It can replace the base ShopDecorator object without affecting how the customer reads the shop name and description.

// Week 3, Pattern Decorator 6
// "Polish Company" Decorator
// Adds a flag before the shop name
public class PolishBadgeDecorator extends ShopDecorator {

    private static final String BADGE_ICON = "🇵🇱 ";
    private static final String BADGE_SUFFIX = " (Kupując tutaj, wspierasz polski kapitał).";

    public PolishBadgeDecorator(ShopDisplay wrappedShop) {
        super(wrappedShop);
    }

    @Override
    public String getDisplayName() {
        return BADGE_ICON + super.getDisplayName();
    }

    @Override
    public String getDisplayDescription() {
        return super.getDisplayDescription() + BADGE_SUFFIX;
    }
}
// End Week 3, Pattern Decorator 6