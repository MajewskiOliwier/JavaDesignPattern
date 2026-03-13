package allnew.okk.shop.decorator;

// Week 3, Pattern Decorator 6
// Dekorator "Polska firma"
// Dodaje flagę przed nazwą sklepu
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
        return super.getDisplayDescription() + " (Kupując tutaj, wspierasz polski kapitał).";
    }
}
// End Week 3, Pattern Decorator 6