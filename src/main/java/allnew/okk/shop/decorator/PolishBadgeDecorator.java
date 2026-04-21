package allnew.okk.shop.decorator;

// Week 8 - Liskov Substitution Principle 3 (Klasa pochodna)
// Klasa pochodna dekoratora. Może zastąpić bazowy obiekt ShopDecorator bez wpływu na to, jak klient odczytuje nazwę i opis sklepu.

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