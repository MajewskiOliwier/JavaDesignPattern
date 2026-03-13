package allnew.okk.shop.decorator;

// Week 3, Pattern Decorator 4
// Dekorator "Premium".
// Dodaje ikonę gwiazdki przed nazwą sklepu.
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
        return super.getDisplayDescription() + " (Sklep rangi PREMIUM).";
    }
}
// End Week 3, Pattern Decorator 4