package allnew.okk.shop.decorator;

// Week 9 - Maintaining Clean Code Principles

// Week 8 - Liskov Substitution Principle 3 (Klasa bazowa)

// Week 3, Pattern Decorator 3
// Abstrakcyjna klasa dekoratora. Implementuje ten sam interfejs co BaseShop.
// Przechowuje instancję "owiniętego" obiektu i domyślnie deleguje do niego wszystkie wywołania.
public abstract class ShopDecorator implements ShopDisplay {
    protected final ShopDisplay wrappedShop;

    public ShopDecorator(ShopDisplay wrappedShop) {
        if (wrappedShop == null) {
            throw new IllegalArgumentException("Dekorowany sklep nie może być pusty.");
        }
        this.wrappedShop = wrappedShop;
    }

    @Override
    public String getDisplayName() {
        return wrappedShop.getDisplayName();
    }

    @Override
    public String getDisplayDescription() {
        return wrappedShop.getDisplayDescription();
    }
}
// End Week 3, Pattern Decorator 3