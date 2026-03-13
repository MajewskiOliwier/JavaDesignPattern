package allnew.okk.shop.decorator;

// Tydzień 3, Wzorzec Decorator 4
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
// Koniec, Tydzień 3, Wzorzec Decorator 4