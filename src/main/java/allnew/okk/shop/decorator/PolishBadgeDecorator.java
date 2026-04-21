package allnew.okk.shop.decorator;

// Week 9 - Maintaining Clean Code Principles

// Week 8 - Liskov Substitution Principle 3 (Klasa pochodna)
// Klasa pochodna dekoratora. Może zastąpić bazowy obiekt ShopDecorator bez wpływu na to, jak klient odczytuje nazwę i opis sklepu.

// Week 3, Pattern Decorator 6
// Dekorator "Polska firma"
// Dodaje flagę przed nazwą sklepu
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