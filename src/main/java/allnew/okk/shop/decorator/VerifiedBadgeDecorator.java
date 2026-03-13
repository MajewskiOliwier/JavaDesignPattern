package allnew.okk.shop.decorator;

// Tydzień 3, Wzorzec Decorator 5
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
// Koniec, Tydzień 3, Wzorzec Decorator 5