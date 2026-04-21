package allnew.okk.shop.decorator;


// Week 8 - Liskov Substitution Principle 3 (Klasa pochodna)
// Klasa pochodna dekoratora. Może zastąpić bazowy obiekt ShopDecorator bez wpływu na to, jak klient odczytuje nazwę i opis sklepu.

// Week 3, Pattern Decorator 5
// Dekorator "Verified"
// Dodaje ikonę przed nazwą sklepu
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
        return super.getDisplayDescription() + " (Sklep ZWERYFIKOWANY).";
    }
}
// End Week 3, Pattern Decorator 5