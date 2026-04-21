package allnew.okk.shop.decorator;


// Week 8 - Liskov Substitution Principle 3 (Klasa pochodna)
// Klasa pochodna dekoratora. Może zastąpić bazowy obiekt ShopDecorator bez wpływu na to, jak klient odczytuje nazwę i opis sklepu.

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